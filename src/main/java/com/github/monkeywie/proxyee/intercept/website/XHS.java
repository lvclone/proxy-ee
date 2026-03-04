package com.github.monkeywie.proxyee.intercept.website;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.util.CacheUtil;
import com.github.monkeywie.proxyee.util.HttpUtil;
import com.github.monkeywie.proxyee.mongodb.service.HttpCacheService;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class XHS {

    public static void addIntercept(HttpProxyInterceptPipeline pipeline, HttpCacheService httpCacheService) {
        pipeline.addLast(new XHSJs());
//        pipeline.addLast(new HttpCacheIntercept(httpCacheService));
    }

}


class XHSJs extends FullResponseIntercept {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(XHSJs.class);

    @Override
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {

        //在匹配到百度首页时插入js
//        return HttpUtil.checkUrl(pipeline.getHttpRequest(), "^www.baidu.com$") && HttpUtil.isHtml(httpRequest, httpResponse);
        boolean result = false;
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        String host = httpRequest.headers().get(HttpHeaderNames.HOST);
        log.info("\nurl: {}\nheaders:{}\n", url, httpRequest.headers());
//        if (host.contains("xhs")) {
        List<String> regexs = new ArrayList<>();
        regexs.add("(.*?)runtime.(.*?).js");
        regexs.add("(.*?)vendor-dynamic.(.*?).js");
        regexs.add("(.*?)vendor.(.*?).js");
        regexs.add("(.*?)fe-platform(.*?).js"); // https://fe-video-qc.xhscdn.com/fe-platform/feebc0adf4ea0006b9c3b69e7336cee982e7d886.js
        regexs.add("(.*?)main.(.*?).js"); // main.js
        regexs.add("(.*?)FeedToNote(.*?).js"); // main.js
        //
        regexs.add("(.*?)splidejs.com(.*?)");
        regexs.add("(.*?)06a4f4679b0957990978b379c7190c52967c141d-9cfd30b667bd56038caf.js");
        regexs.add("(.*?)fb7d5399-35116b9042877d82c774.js");
//        regexs.add("(.*?).png(.*?)"); // main.js
        // view
//        regexs.add("(.*?).js");
//        regexs.add("(.*?).css");

        for (String regex : regexs) {
            result = HttpUtil.checkUrl(url, String.format("^%s$", regex));
            if (result) {
                log.info("result: {}", result);
                return result;
            }
        }
//        } else
        if (url.contains("wanderprints.com")) {
            result = HttpUtil.checkUrl(url, "products.json");
        }

        return result;
    }

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
//        CacheUtil cacheUtil = new CacheUtil("xhs");
        CacheUtil cacheUtil = new CacheUtil("splidejs");
        cacheUtil.overResp(httpResponse, pipeline);
    }

}


