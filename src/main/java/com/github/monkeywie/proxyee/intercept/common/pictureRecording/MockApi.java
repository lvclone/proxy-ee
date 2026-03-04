package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import com.github.monkeywie.proxyee.intercept.HttpProxyIntercept;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.util.HttpUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockApi extends HttpProxyIntercept {

    @Override
    public void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline)  {
//        log.info("MockApi beforeRequest url:{} headers:{}", HttpUtil.buildUrl(pipeline.getHttpRequest()), httpRequest.headers());
//        String html = "<html><body><div style=\"margin-top:100px;text-align:center;\"><a href=\"ca.crt\">ProxyeeRoot aaaacaaaaa.crt</a></div></body></html>";
//        ByteUtil.buildContext(clientChannel, "text/html;charset=utf-8", HttpHeaderValues.KEEP_ALIVE, html.getBytes());

        try {
            new PictureRecordingCacheUtil().beforeRequest(clientChannel, httpRequest, pipeline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
