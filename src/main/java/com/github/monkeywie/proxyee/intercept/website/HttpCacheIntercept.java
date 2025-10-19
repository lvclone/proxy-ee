package com.github.monkeywie.proxyee.intercept.website;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.mongodb.service.HttpCacheService;
import com.github.monkeywie.proxyee.util.ByteUtil;
import com.github.monkeywie.proxyee.util.HttpUtil;
import com.github.monkeywie.proxyee.util.github.CacheUtil;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpCacheIntercept extends FullResponseIntercept {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(HttpCacheIntercept.class);

    HttpCacheService httpCacheService;

    public HttpCacheIntercept(HttpCacheService httpCacheService) {
        super();
        this.httpCacheService = httpCacheService;
    }

    @Override
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {

//        log.info("header: {}", httpRequest.headers());
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        boolean result = false;
        if (url != null) {
            // 域名
            List<String> domainNames = new ArrayList<String>() {{
                add("xhscdn.com");
                add("xiaohongshu.com");
//                add("shop-vite");
            }};
            for (String domainName : domainNames) {
                if (url.matches("^(.*?)" + domainName + "(.*?)$")) {
                    result = true;
                    break;
//                        return true;
                }
            }

            log.info("\n> > > > > > > > > > > > > > > > > > > > \nurl: {} {}\n", url, result);

        }

        return result;
    }

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        // 文件类型
        List<String> fileTypes = new ArrayList<String>() {{
            add("application/javascript");
//            add("application/xml");
            add("text/javascript");
            add("text/html; charset=utf-8");
        }};
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} \n", url, httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE));

        if (fileTypes.contains(httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE))) {
            //  打印原始响应信息
            String context = httpResponse.content().toString(Charset.defaultCharset());

            if (url != null) {
                CacheUtil cacheUtil = new CacheUtil();

                String result = cacheUtil.readResult(getPath(url));
                if (result != null && !result.isEmpty()) {
                    ByteUtil.cover(httpResponse.content(), result);
                } else {
                    cacheUtil.saveResult(getPath(url), context);
                }
            }
        }
    }


    public static String getPath(String url) {
        return String.format("%s", url.replaceAll("/", "_"));
    }


}
