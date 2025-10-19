package com.github.monkeywie.proxyee.nitm.listener;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.nitm.ConnectionContext;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @Description:
 * @Author: lv.mr
 * @Date: 2025-03-30 17:38
 * @Version: 1.0
 */
public interface ProxyeeProxyListener {

    /**
     * This callback will be invoked when the client channel was first initialized.
     *
     * @param connectionContext the connection context
     * @param clientChannel     the client channel
     */
    default void onInit(ConnectionContext connectionContext, Channel clientChannel) {
    }

    /**
     * 在与目标服务器建立连接之前拦截
     */
    default void beforeConnect(Channel clientChannel, HttpProxyInterceptPipeline pipeline) throws Exception {
        pipeline.beforeConnect(clientChannel);
    }

    /**
     * 拦截代理服务器到目标服务器的请求头
     */
    default void beforeRequest(Channel clientChannel, HttpRequest httpRequest,
                               HttpProxyInterceptPipeline pipeline) throws Exception {
        pipeline.beforeRequest(clientChannel, httpRequest);
    }

    /**
     * 拦截代理服务器到目标服务器的请求体
     */
    default void beforeRequest(Channel clientChannel, HttpContent httpContent,
                                      HttpProxyInterceptPipeline pipeline) throws Exception {
        pipeline.beforeRequest(clientChannel, httpContent);
    }

    /**
     * 拦截代理服务器到客户端的响应头
     */
    default void afterResponse(Channel clientChannel, Channel proxyChannel, HttpResponse httpResponse,
                                      HttpProxyInterceptPipeline pipeline) throws Exception {
        pipeline.afterResponse(clientChannel, proxyChannel, httpResponse);
    }

    /**
     * 拦截代理服务器到客户端的响应体
     */
    default void afterResponse(Channel clientChannel, Channel proxyChannel, HttpContent httpContent,
                                      HttpProxyInterceptPipeline pipeline)
            throws Exception {
        pipeline.afterResponse(clientChannel, proxyChannel, httpContent);
    }



//    List<HttpObject> onHttpResponse(ConnectionContext connectionContext, HttpObject response);
//
//    void onForwardResponse(ConnectionContext connectionContext, ByteBuf byteBuf);
//
//    void close(ConnectionContext connectionContext);
}
