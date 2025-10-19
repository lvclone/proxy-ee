package com.github.monkeywie.proxyee;

import com.github.monkeywie.proxyee.config.ConfigDefault;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.MockApi;
import com.github.monkeywie.proxyee.intercept.website.XHS;
import com.github.monkeywie.proxyee.nitm.listener.ProxyeeProxyListenerStore;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProxyServer {

    public static void main(String[] args) throws Exception {
        HttpProxyServerConfig config = ConfigDefault.getHttpProxyServerConfig();
        new HttpProxyServer()
                .serverConfig(config)
                .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
                    @Override
                    public void init(HttpProxyInterceptPipeline pipeline) {
                        pipeline.addLast(new MockApi());
//                        PictureRecording.addIntercept(pipeline);
                        XHS.addIntercept(pipeline, null);
                    }
                })
                .start(9999);
    }

}
