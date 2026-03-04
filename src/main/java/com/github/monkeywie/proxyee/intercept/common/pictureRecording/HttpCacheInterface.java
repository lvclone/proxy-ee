package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

public interface HttpCacheInterface {

    public void save(String key, HttpCache value);

    public HttpCache read(String key);

    void handleResponse(FullHttpResponse httpResponse, String url);

    void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline);

}
