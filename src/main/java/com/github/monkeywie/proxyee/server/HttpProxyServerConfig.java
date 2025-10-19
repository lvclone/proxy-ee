package com.github.monkeywie.proxyee.server;

import com.github.monkeywie.proxyee.intercept.HttpProxyIntercept;
import com.github.monkeywie.proxyee.nitm.listener.ProxyeeProxyListenerStore;
import com.github.monkeywie.proxyee.server.accept.HttpProxyAcceptHandler;
import com.github.monkeywie.proxyee.server.accept.HttpProxyMitmMatcher;
import com.github.monkeywie.proxyee.server.auth.HttpProxyAuthenticationProvider;
import com.github.monkeywie.proxyee.config.IdleStateCheck;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.handler.ssl.SslContext;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.Data;
import java.net.SocketAddress;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;

@Data
public class HttpProxyServerConfig {


    // 握手协议指纹
    private SslContext clientSslCtx;

    // 读取CA证书使用者信息
    private String issuer;

    // 读取CA证书有效时段(server证书有效期超出CA证书的，在手机上会提示证书不安全)
    private Date caNotBefore;
    private Date caNotAfter;

    // CA私钥
    private PrivateKey caPriKey;

    // 随机公私钥用于网站SSL证书动态创建
    private PrivateKey serverPriKey;
    private PublicKey serverPubKey;

    private EventLoopGroup proxyLoopGroup;
    private int bossGroupThreads;
    private int workerGroupThreads;
    private int proxyGroupThreads;

    // https 处理
    private boolean handleSsl;

    // 拦截器
    private List<HttpProxyIntercept> intercepts;
    private ProxyeeProxyListenerStore listenerStore;

    private HttpProxyAcceptHandler httpProxyAcceptHandler;
    private HttpProxyAuthenticationProvider authenticationProvider;
    private HttpProxyMitmMatcher mitmMatcher;
    private final AddressResolverGroup<? extends SocketAddress> resolver;
    private Iterable<String> ciphers;
    private int maxInitialLineLength = HttpObjectDecoder.DEFAULT_MAX_INITIAL_LINE_LENGTH;
    private int maxHeaderSize = HttpObjectDecoder.DEFAULT_MAX_HEADER_SIZE;
    private int maxChunkSize = HttpObjectDecoder.DEFAULT_MAX_CHUNK_SIZE;
    private IdleStateCheck idleStateCheck;

    public HttpProxyServerConfig() {
        this(DefaultAddressResolverGroup.INSTANCE);
    }

    public HttpProxyServerConfig(final AddressResolverGroup<? extends SocketAddress> resolver) {
        this.resolver = resolver;
    }

    public AddressResolverGroup<?> resolver() {
        return resolver;
    }

}
