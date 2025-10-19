package com.github.monkeywie.proxyee.config;

import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConfigDefault {

    public static HttpProxyServerConfig getHttpProxyServerConfig() {
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
}
