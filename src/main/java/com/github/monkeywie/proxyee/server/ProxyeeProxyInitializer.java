package com.github.monkeywie.proxyee.server;

import com.github.monkeywie.proxyee.nitm.Address;
import com.github.monkeywie.proxyee.nitm.ConnectionContext;
import com.github.monkeywie.proxyee.config.IdleStateCheck;
import com.github.monkeywie.proxyee.exception.HttpProxyExceptionHandle;
import com.github.monkeywie.proxyee.handler.HttpProxyServerHandler;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.nitm.ProxyeeProxyMaster;
import com.github.monkeywie.proxyee.proxy.ProxyConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lv.mr
 * @Date: 2025-03-30 15:28
 * @Version: 1.0
 */
public class ProxyeeProxyInitializer extends ChannelInitializer<Channel> {

    private ProxyeeProxyMaster master;

    @Override
    protected void initChannel(Channel ch) {
        InetSocketAddress address = (InetSocketAddress) ch.remoteAddress();
        Address clientAddress = new Address(address.getHostName(), address.getPort());

        ConnectionContext context = new ConnectionContext(master)
                .withClientAddr(clientAddress)
                .withClientChannel(ch)
                .withAlloc(ch.alloc());

        context.listener().onInit(context, ch);

        HttpProxyServerConfig serverConfig = master.getServerConfig();

        ch.pipeline().addLast("httpCodec", new HttpServerCodec(
                serverConfig.getMaxInitialLineLength(),
                serverConfig.getMaxHeaderSize(),
                serverConfig.getMaxChunkSize()));


        if (serverConfig.getIdleStateCheck() != null) {
            IdleStateCheck idleStateCheck = serverConfig.getIdleStateCheck();
            ch.pipeline().addLast("idleStateCheck",
                    new IdleStateHandler(idleStateCheck.getReaderIdleTime(), idleStateCheck.getWriterIdleTime(),
                            idleStateCheck.getAllIdleTime(), TimeUnit.MILLISECONDS)
            );
        }

        ch.pipeline().addLast(HttpProxyServerHandler.key, context.provider().getHttpProxyServerHandler());
    }


    public ProxyeeProxyInitializer(HttpProxyServerConfig serverConfig,
                                   HttpProxyInterceptInitializer proxyInterceptInitializer,
                                   ProxyConfig proxyConfig,
                                   HttpProxyExceptionHandle httpProxyExceptionHandle) {
        this(new ProxyeeProxyMaster(serverConfig, proxyInterceptInitializer, proxyConfig, httpProxyExceptionHandle));
    }

    public ProxyeeProxyInitializer(ProxyeeProxyMaster proxyeeProxyMaster) {
        this.master = proxyeeProxyMaster;
    }
}
