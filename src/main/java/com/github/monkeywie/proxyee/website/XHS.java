package com.github.monkeywie.proxyee.website;

import com.alibaba.fastjson.JSON;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.util.ByteUtil;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;

public class XHS {

    public static void addIntercept(HttpProxyInterceptPipeline pipeline) {
        pipeline.addLast(new RunTimeJs());
    }

}

class RunTimeJs extends FullResponseIntercept {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(RunTimeJs.class);

    @Override
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {

//        log.info("req:{} {} {}", httpRequest.protocolVersion(), httpRequest.getClass(), httpRequest.headers());
        log.info("req:{}\nheaders: {}", httpRequest, JSON.toJSONString(httpRequest.headers()));

        //在匹配到百度首页时插入js
//        return HttpUtil.checkUrl(pipeline.getHttpRequest(), "^www.baidu.com$") && HttpUtil.isHtml(httpRequest, httpResponse);
//        boolean result = HttpUtil.checkUrl(pipeline.getHttpRequest(), "^(.*?)runtime.(.*?).js$");
        // zfs
//        boolean result = HttpUtil.checkUrl(pipeline.getHttpRequest(), "^(.*?).zfs.(.*?)$");
//        log.info("result: {}", result);
//        return result;
        return true;
    }

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        //打印原始响应信息
//        log.info("resp: {}", httpResponse.toString());
        log.info("resp: {}", httpResponse.content().toString(Charset.defaultCharset()));
        //修改响应头和响应体
        httpResponse.headers().set("handel", "edit head");

        log.info("content.class: {}", httpResponse.content().getClass());

        String str = "var e,t,n,r,o,i={},u={};";
        int index = ByteUtil.findText(httpResponse.content(), str);
        ByteUtil.insertText(httpResponse.content(), index, "window.U = u;window.I = i;");
        log.info("resp: {}", httpResponse.content().toString(Charset.defaultCharset()));


        httpResponse.content().writeBytes("<script>alert('hello proxyee')</script>".getBytes());
    }

}
