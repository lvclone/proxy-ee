package com.github.monkeywie.proxyee.nitm.listener;

import com.github.monkeywie.proxyee.nitm.ConnectionContext;
import com.github.monkeywie.proxyee.nitm.listener.common.CertDown;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import java.util.List;

/**
 * @Description: proxyee
 * @Author: lv.mr
 * @Date: 2025-03-30 17:09
 * @Version: 1.0
 */
public class ProxyeeProxyListenerManager implements ProxyeeProxyListener {

    private final List<ProxyeeProxyListener> listeners;

    private final List<ProxyeeProxyListener> reversedListeners;

    public ProxyeeProxyListenerManager(List<ProxyeeProxyListener> listeners) {
        this.listeners =
                ImmutableList.<ProxyeeProxyListener>builder().add(new CertDown()).addAll(listeners).build();
        this.reversedListeners = Lists.reverse(this.listeners);
    }

    @Override
    public void onInit(ConnectionContext connectionContext, Channel clientChannel) {
        listeners.forEach(listener -> listener.onInit(connectionContext, clientChannel));
    }








//    @Override
//    public List<HttpObject> onHttpResponse(ConnectionContext connectionContext, HttpObject response) {
//        return reversedListeners
//                .stream()
//                .reduce(
//                        ImmutableList.of(response), (objects, listener) ->
//                                objects.stream()
//                                        .flatMap(f -> listener.onHttpResponse(connectionContext, f).stream())
//                                        .collect(ImmutableList.toImmutableList()), (accu, objects) -> objects);
//    }
//
//    @Override
//    public void onForwardResponse(ConnectionContext connectionContext, ByteBuf byteBuf) {
//        reversedListeners.forEach(listener -> listener.onForwardResponse(connectionContext, byteBuf));
//    }
//
//    @Override
//    public void close(ConnectionContext connectionContext) {
//        reversedListeners.forEach(listener -> listener.close(connectionContext));
//    }

}
