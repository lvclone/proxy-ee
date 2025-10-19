package com.github.monkeywie.proxyee.nitm.listener;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description:
 * @Author: lv.mr
 * @Date: 2025-03-30 17:31
 * @Version: 1.0
 */
public class ProxyeeProxyListenerStore {
    private List<ProxyeeProxyListenerProvider> listeners;

    public ProxyeeProxyListenerStore() {
        this(new ArrayList<>());
    }

    public ProxyeeProxyListenerStore(
            List<ProxyeeProxyListenerProvider> listeners) {
        this.listeners = listeners;
    }

    /**
     * Add the provider to the last place of the store.
     *
     * @param provider the listener
     * @return the store itself
     */
    public ProxyeeProxyListenerStore add(ProxyeeProxyListenerProvider provider) {
        listeners.add(provider);
        return this;
    }

    public List<ProxyeeProxyListenerProvider> getListeners() {
        return listeners;
    }


}
