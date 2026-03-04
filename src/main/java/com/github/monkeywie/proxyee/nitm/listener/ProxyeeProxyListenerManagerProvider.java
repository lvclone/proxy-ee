package com.github.monkeywie.proxyee.nitm.listener;

import static com.google.common.collect.ImmutableList.toImmutableList;

/**
 * @Description:
 * @Author: lv.mr
 * @Date: 2025-03-30 17:26
 * @Version: 1.0
 */
public class ProxyeeProxyListenerManagerProvider implements ProxyeeProxyListenerProvider{
    private ProxyeeProxyListenerStore listenerStore;

    public ProxyeeProxyListenerManagerProvider(ProxyeeProxyListenerStore listenerStore) {
        this.listenerStore = listenerStore;
    }

    @Override
    public ProxyeeProxyListener create() {
        return new ProxyeeProxyListenerManager(listenerStore.getListeners().stream()
                .map(ProxyeeProxyListenerProvider::create)
                .collect(toImmutableList()));
    }

    @Override
    public Class<? extends ProxyeeProxyListener> listenerClass() {
        return ProxyeeProxyListenerManager.class;
    }

}
