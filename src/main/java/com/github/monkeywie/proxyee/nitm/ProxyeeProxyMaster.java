package com.github.monkeywie.proxyee.nitm;

import com.github.monkeywie.proxyee.exception.HttpProxyExceptionHandle;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.nitm.listener.ProxyeeProxyListenerManagerProvider;
import com.github.monkeywie.proxyee.proxy.ProxyConfig;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.ViewFactory;

/**
 * @Description:
 * @Author: lv.mr
 * @Date: 2025-03-30 15:34
 * @Version: 1.0
 */
@Data
public class ProxyeeProxyMaster {
    private HttpProxyServerConfig serverConfig;

    private HttpProxyInterceptInitializer interceptInitializer;

    private ProxyConfig proxyConfig;

    private HttpProxyExceptionHandle exceptionHandle;

    private ProxyeeProxyListenerManagerProvider provider;

    public ProxyeeProxyMaster(HttpProxyServerConfig serverConfig,
                              HttpProxyInterceptInitializer interceptInitializer,
                              ProxyConfig proxyConfig,
                              HttpProxyExceptionHandle exceptionHandle) {
        this.serverConfig = serverConfig;
        this.interceptInitializer = interceptInitializer;
        this.proxyConfig = proxyConfig;
        this.exceptionHandle = exceptionHandle;
        this.provider = new ProxyeeProxyListenerManagerProvider(getServerConfig().getListenerStore());
    }

    public ProxyeeProxyListenerManagerProvider listenerProvider() {
        return provider;
    }

    public HandlerProvider provider(ConnectionContext connectionContext) {
        return new HandlerProvider(this, connectionContext);
    }

//    public ChannelFuture connect(ChannelHandlerContext fromCtx, ConnectionContext connectionContext,
//                                 ChannelHandler handler) {
//        return backendChannelBootstrap.connect(fromCtx, connectionContext, handler);
//    }

}
