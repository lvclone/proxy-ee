package com.github.monkeywie.proxyee;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.CertDownIntercept;
import com.github.monkeywie.proxyee.website.XHS;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class ProxyServer {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(ProxyServer.class);


    public static void main(String[] args) throws Exception {
        HttpProxyServerConfig config = getHttpProxyServerConfig();
        new HttpProxyServer()
                .serverConfig(config)
                .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
                    @Override
                    public void init(HttpProxyInterceptPipeline pipeline) {
                        pipeline.addLast(new CertDownIntercept());
                        XHS.addIntercept(pipeline, null);
                    }
                })
                .start(9999);
    }

    private static HttpProxyServerConfig getHttpProxyServerConfig() {
        HttpProxyServerConfig config = new HttpProxyServerConfig();
        config.setHandleSsl(true);
        // 设置Ciphers 用于改变 Client Hello 握手协议指纹
        Set<String> defaultCiphers = new LinkedHashSet<String>();
        defaultCiphers.add("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_RSA_WITH_AES_128_CBC_SHA");
        defaultCiphers.add("TLS_RSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        config.setCiphers(defaultCiphers);
        return config;
    }


    /*

http://p3.h5-x.com/dSe9PNlYozwhSU57
    hph54q6kLhNKaw0l
    Z7mZ6hHZLUK8NYbZ
    dSe9PNlYozwhSU57

     */

}
