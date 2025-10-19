package com.github.monkeywie.proxyee.nitm;

import com.github.monkeywie.proxyee.handler.HttpProxyServerHandler;

/**
 * <p>
 * 处理器
 * </p>
 *
 * @author lv.mr
 * @since 2025/3/30 16:28
 */
public class HandlerProvider {

    private ProxyeeProxyMaster master;
    private ConnectionContext context;

    public HandlerProvider(ProxyeeProxyMaster proxyeeProxyMaster, ConnectionContext connectionContext) {
        this.master = proxyeeProxyMaster;
        this.context = connectionContext;
    }

    public HttpProxyServerHandler getHttpProxyServerHandler() {
        return new HttpProxyServerHandler(context);
    }
}
