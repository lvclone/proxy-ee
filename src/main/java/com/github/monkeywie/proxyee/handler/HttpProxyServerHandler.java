package com.github.monkeywie.proxyee.handler;

import com.github.monkeywie.proxyee.crt.CertPool;
import com.github.monkeywie.proxyee.intercept.HttpProxyIntercept;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.nitm.ConnectionContext;
import com.github.monkeywie.proxyee.proxy.ProxyHandleFactory;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.auth.HttpAuthContext;
import com.github.monkeywie.proxyee.server.auth.HttpProxyAuthenticationProvider;
import com.github.monkeywie.proxyee.server.auth.model.HttpToken;
import com.github.monkeywie.proxyee.util.ProtoUtil;
import com.github.monkeywie.proxyee.util.ProtoUtil.RequestProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.resolver.NoopAddressResolverGroup;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpProxyServerHandler extends ChannelInboundHandlerAdapter {

    public static final String key = "serverHandle";

    private ChannelFuture channelFuture;
    private RequestProto requestProto;
    private int status = 0;
    private HttpProxyInterceptPipeline interceptPipeline;
    private List requestList;
    private Boolean isConnect;
    private byte[] httpTagBuf;
    private ConnectionContext context;

    public HttpProxyServerHandler(ConnectionContext context) {
        this.context = context;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            DecoderResult result = request.decoderResult();
            Throwable cause = result.cause();

            if (cause instanceof DecoderException) {
                setStatus(2);
                HttpResponseStatus status = null;

                if (cause instanceof TooLongHttpLineException) {
                    status = HttpResponseStatus.REQUEST_URI_TOO_LONG;
                } else if (cause instanceof TooLongHttpHeaderException) {
                    status = HttpResponseStatus.REQUEST_HEADER_FIELDS_TOO_LARGE;
                } else if (cause instanceof TooLongHttpContentException) {
                    status = HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE;
                }

                if (status == null) {
                    status = HttpResponseStatus.BAD_REQUEST;
                }

                HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
                ctx.writeAndFlush(response);
                //ctx.channel().pipeline().remove("httpCodec");
                ReferenceCountUtil.release(msg);
                return;
            }

            // The first time a connection is established, the host and port number are taken and the proxy handshake is processed.
            if (getStatus() == 0) {
                setRequestProto(ProtoUtil.getRequestProto(request));
                if (getRequestProto() == null) { // bad request
                    ctx.channel().close();
                    return;
                }
                // 首次连接处理
                if (getContext().getMaster().getServerConfig().getHttpProxyAcceptHandler() != null
                        && !getContext().getMaster().getServerConfig().getHttpProxyAcceptHandler().onAccept(request, ctx.channel())) {
                    setStatus(2);
                    ctx.channel().close();
                    return;
                }
                // 代理身份验证
                if (!authenticate(ctx, request)) {
                    setStatus(2);
                    ctx.channel().close();
                    return;
                }
                setStatus(1);
                if (HttpMethod.CONNECT.name().equalsIgnoreCase(request.method().name())) {// 建立代理握手
                    setStatus(2);
                    HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpProxyServer.SUCCESS);
                    ctx.writeAndFlush(response);
                    ctx.channel().pipeline().remove("httpCodec");
                    // fix issue #42
                    ReferenceCountUtil.release(msg);
                    return;
                }
            }
            setInterceptPipeline(buildPipeline());
            getInterceptPipeline().setRequestProto(getRequestProto().copy());
            // fix issue #27
            if (request.uri().indexOf("/") != 0) {
                URL url = new URL(request.uri());
                request.setUri(url.getFile());
            }
            getInterceptPipeline().beforeRequest(ctx.channel(), request);
            ReferenceCountUtil.release(msg);
        } else if (msg instanceof HttpContent) {
            if (getStatus() != 2) {
                getInterceptPipeline().beforeRequest(ctx.channel(), (HttpContent) msg);
            } else {
                ReferenceCountUtil.release(msg);
                setStatus(1);
            }
        } else { // ssl和websocket的握手处理
            ByteBuf byteBuf = (ByteBuf) msg;
            if (getContext().getMaster().getServerConfig().isHandleSsl() && byteBuf.getByte(0) == 22 && doMitm()) {// ssl握手
                getRequestProto().setSsl(true);
                int port = ((InetSocketAddress) ctx.channel().localAddress()).getPort();
                SslContext sslCtx = SslContextBuilder
                        .forServer(getContext().getMaster().getServerConfig().getServerPriKey(), CertPool.getCert(port, getRequestProto().getHost(), getContext().getMaster().getServerConfig())).build();
                ctx.pipeline().addFirst("httpCodec", new HttpServerCodec(
                        getContext().getMaster().getServerConfig().getMaxInitialLineLength(),
                        getContext().getMaster().getServerConfig().getMaxHeaderSize(),
                        getContext().getMaster().getServerConfig().getMaxChunkSize()));
                ctx.pipeline().addFirst("sslHandle", sslCtx.newHandler(ctx.alloc()));
                // 重新过一遍pipeline，拿到解密后的的http报文
                ctx.pipeline().fireChannelRead(msg);
                return;
            }

            if (byteBuf.readableBytes() < 8) {
                httpTagBuf = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(httpTagBuf);
                ReferenceCountUtil.release(msg);
                return;
            }
            if (httpTagBuf != null) {
                byte[] tmp = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(tmp);
                byteBuf.writeBytes(httpTagBuf);
                byteBuf.writeBytes(tmp);
                httpTagBuf = null;
            }

            // 如果connect后面跑的是HTTP报文，也可以抓包处理
            if (isHttp(byteBuf)) {
                ctx.pipeline().addFirst("httpCodec", new HttpServerCodec(
                        getContext().getMaster().getServerConfig().getMaxInitialLineLength(),
                        getContext().getMaster().getServerConfig().getMaxHeaderSize(),
                        getContext().getMaster().getServerConfig().getMaxChunkSize()));
                ctx.pipeline().fireChannelRead(msg);
                return;
            }
            handleProxyData(ctx.channel(), msg, false);
        }
    }

    private boolean doMitm() {
        return getContext().getMaster().getServerConfig().getMitmMatcher() == null || getContext().getMaster().getServerConfig().getMitmMatcher().doMatch(getRequestProto());
    }

    private boolean isHttp(ByteBuf byteBuf) {
        byte[] bytes = new byte[8];
        byteBuf.getBytes(0, bytes);
        String methodToken = new String(bytes);
        return methodToken.startsWith("GET ") || methodToken.startsWith("POST ") || methodToken.startsWith("HEAD ")
                || methodToken.startsWith("PUT ") || methodToken.startsWith("DELETE ") || methodToken.startsWith("OPTIONS ")
                || methodToken.startsWith("CONNECT ") || methodToken.startsWith("TRACE ");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        if (getChannelFuture() != null) {
            getChannelFuture().channel().close();
        }
        ctx.channel().close();
        if (getContext().getMaster().getServerConfig().getHttpProxyAcceptHandler() != null) {
            getContext().getMaster().getServerConfig().getHttpProxyAcceptHandler().onClose(ctx.channel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (getChannelFuture() != null) {
            getChannelFuture().channel().close();
        }
        ctx.channel().close();
        getContext().getMaster().getExceptionHandle().beforeCatch(ctx.channel(), cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.channel().close();
        }
    }

    private boolean authenticate(ChannelHandlerContext ctx, HttpRequest request) {
        if (getContext().getMaster().getServerConfig().getAuthenticationProvider() != null) {
            HttpProxyAuthenticationProvider authProvider = getContext().getMaster().getServerConfig().getAuthenticationProvider();

            // Disable auth for request?
            if (!authProvider.matches(request)) {
                return true;
            }

            HttpToken httpToken = authProvider.authenticate(request);
            if (httpToken == null) {
                HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpProxyServer.UNAUTHORIZED);
                response.headers().set(HttpHeaderNames.PROXY_AUTHENTICATE, authProvider.authType() + " realm=\"" + authProvider.authRealm() + "\"");
                ctx.writeAndFlush(response);
                return false;
            }
            HttpAuthContext.setToken(ctx.channel(), httpToken);
        }
        return true;
    }

    private void handleProxyData(Channel channel, Object msg, boolean isHttp) throws Exception {
        if (getInterceptPipeline() == null) {
            setInterceptPipeline(buildOnlyConnectPipeline());
            getInterceptPipeline().setRequestProto(getRequestProto().copy());
        }
        RequestProto pipeRp = getInterceptPipeline().getRequestProto();
        boolean isChangeRp = false;
        if (isHttp && msg instanceof HttpRequest) {
            // check if request modified
            if (!pipeRp.equals(getRequestProto())) {
                isChangeRp = true;
            }
        }

        if (isChangeRp || getChannelFuture() == null) {
            // connection异常 还有HttpContent进来，不转发
            if (isHttp && !(msg instanceof HttpRequest)) {
                return;
            }
            getInterceptPipeline().beforeConnect(channel);

            // 默认情况下，我们使用 pipeline 中设置的 proxy 配置
            // by default, we use the proxy config set in the pipeline
            ProxyHandler proxyHandler = ProxyHandleFactory.build(getInterceptPipeline().getProxyConfig() == null ?
                    getContext().getMaster().getProxyConfig() : getInterceptPipeline().getProxyConfig());

            /*
             * 添加SSL client hello的Server Name Indication extension(SNI扩展) 有些服务器对于client
             * hello不带SNI扩展时会直接返回Received fatal alert: handshake_failure(握手错误)
             * 例如：https://cdn.mdn.mozilla.net/static/img/favicon32.7f3da72dcea1.png
             */
            ChannelInitializer channelInitializer = isHttp ? new HttpProxyInitializer(channel, pipeRp, proxyHandler)
                    : new TunnelProxyInitializer(channel, proxyHandler);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(getContext().getMaster().getServerConfig().getProxyLoopGroup()) // 注册线程池
                    .channel(NioSocketChannel.class) // 使用NioSocketChannel来作为连接用的channel类
                    .handler(channelInitializer);
            if (proxyHandler != null) {
                // 代理服务器解析DNS和连接
                bootstrap.resolver(NoopAddressResolverGroup.INSTANCE);
            } else {
                bootstrap.resolver(getContext().getMaster().getServerConfig().resolver());
            }
            setRequestList(new LinkedList());
            setChannelFuture(bootstrap.connect(pipeRp.getHost(), pipeRp.getPort()));
            getChannelFuture().addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    future.channel().writeAndFlush(msg);
                    synchronized (getRequestList()) {
                        getRequestList().forEach(obj -> future.channel().writeAndFlush(obj));
                        getRequestList().clear();
                        setIsConnect(true);
                    }
                } else {
                    synchronized (getRequestList()) {
                        getRequestList().forEach(obj -> ReferenceCountUtil.release(obj));
                        getRequestList().clear();
                    }
                    getContext().getMaster().getExceptionHandle().beforeCatch(channel, future.cause());
                    future.channel().close();
                    channel.close();
                }
            });
        } else {
            synchronized (getRequestList()) {
                if (getIsConnect()) {
                    getChannelFuture().channel().writeAndFlush(msg);
                } else {
                    getRequestList().add(msg);
                }
            }
        }
    }

    private HttpProxyInterceptPipeline buildPipeline() {
        HttpProxyInterceptPipeline interceptPipeline = new HttpProxyInterceptPipeline(new HttpProxyIntercept() {
            @Override
            public void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline)
                    throws Exception {
                handleProxyData(clientChannel, httpRequest, true);
            }

            @Override
            public void beforeRequest(Channel clientChannel, HttpContent httpContent, HttpProxyInterceptPipeline pipeline)
                    throws Exception {
                handleProxyData(clientChannel, httpContent, true);
            }

            @Override
            public void afterResponse(Channel clientChannel, Channel proxyChannel, HttpResponse httpResponse,
                                      HttpProxyInterceptPipeline pipeline) throws Exception {
                clientChannel.writeAndFlush(httpResponse);
                if (HttpHeaderValues.WEBSOCKET.toString().equals(httpResponse.headers().get(HttpHeaderNames.UPGRADE))) {
                    // websocket转发原始报文
                    proxyChannel.pipeline().remove("httpCodec");
                    clientChannel.pipeline().remove("httpCodec");
                }
            }

            @Override
            public void afterResponse(Channel clientChannel, Channel proxyChannel, HttpContent httpContent,
                                      HttpProxyInterceptPipeline pipeline) throws Exception {
                clientChannel.writeAndFlush(httpContent);
            }
        });
        getContext().getMaster().getInterceptInitializer().init(interceptPipeline);
        return interceptPipeline;
    }

    // fix issue #186: 不拦截https报文时，暴露一个扩展点用于代理设置，并且保持一致的编程接口
    private HttpProxyInterceptPipeline buildOnlyConnectPipeline() {
        HttpProxyInterceptPipeline interceptPipeline = new HttpProxyInterceptPipeline(new HttpProxyIntercept());
        getContext().getMaster().getInterceptInitializer().init(interceptPipeline);
        return interceptPipeline;
    }
}
