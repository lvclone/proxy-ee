package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.util.HttpUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

class Recording extends FullResponseIntercept {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(PictureRecording.class);

    PictureRecordingCacheUtil pictureRecordingCacheUtil = new PictureRecordingCacheUtil();

    @Override
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        return true;
    }

    @Override
    public void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline) throws Exception {
        pictureRecordingCacheUtil.beforeRequest(clientChannel, httpRequest, pipeline);
    }

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {

        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} \n httpResponse.headers: {} \n", url, httpRequest.headers(), httpResponse.headers());
        pictureRecordingCacheUtil.handleResponse(httpResponse, url);
    }

}
