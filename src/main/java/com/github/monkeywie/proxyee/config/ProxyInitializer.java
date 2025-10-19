package com.github.monkeywie.proxyee.config;

import com.github.monkeywie.proxyee.handler.HttpProxyServerHandler;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ProxyInitializer extends ChannelInitializer<Channel> {
    HttpProxyServerConfig serverConfig;
    HttpProxyServerHandler httpProxyServerHandler;

    public ProxyInitializer() {}

    public ProxyInitializer(HttpProxyServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public ProxyInitializer(HttpProxyServerConfig serverConfig, HttpProxyServerHandler httpProxyServerHandler) {
        this.serverConfig = serverConfig;
        this.httpProxyServerHandler = httpProxyServerHandler;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
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
        ch.pipeline().addLast("serverHandle",
                this.httpProxyServerHandler);
    }

}
