package com.github.monkeywie.proxyee.nitm;

import com.github.monkeywie.proxyee.nitm.listener.ProxyeeProxyListener;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import lombok.Data;

/**
 * @Description: 连接内容
 * @Author: lv.mr
 * @Date: 2025-03-30 15:52
 * @Version: 1.0
 */
@Data
public class ConnectionContext {

    private ProxyeeProxyMaster master;

    private Address address;

    private Channel channel;

    private ByteBufAllocator allocator;

    private ProxyeeProxyListener listener;

    HandlerProvider provider;


    public ConnectionContext(ProxyeeProxyMaster master) {
        this.master = master;
        this.provider = master.provider(this);
        this.listener = master.listenerProvider().create();
    }

    public ConnectionContext withClientAddr(Address address) {
        this.address = address;
        return this;
    }

    public ConnectionContext withClientChannel(Channel ch) {
        this.channel = ch;
        return this;
    }

    public ConnectionContext withAlloc(ByteBufAllocator alloc) {
        this.allocator = alloc;
        return this;
    }

    public ProxyeeProxyListener listener() {
        return listener;
    }

    public HandlerProvider provider() {
        return provider;
    }

}
