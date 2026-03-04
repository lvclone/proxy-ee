package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

public class LocalCache implements HttpCacheInterface {


    @Override
    public void save(String key, HttpCache value) {

    }

    @Override
    public HttpCache read(String key) {
        return null;
    }

    @Override
    public void handleResponse(FullHttpResponse httpResponse, String url) {

    }

    @Override
    public void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline) {

    }

}
