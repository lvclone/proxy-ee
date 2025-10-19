package com.github.monkeywie.proxyee.website;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.util.ByteUtil;
import com.github.monkeywie.proxyee.util.HttpUtil;
import com.github.monkeywie.proxyee.mongodb.service.HttpCacheService;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;
public class XHS {

    public static void addIntercept(HttpProxyInterceptPipeline pipeline, HttpCacheService httpCacheService) {
//        pipeline.addLast(new RunTimeJs());
        pipeline.addLast(new HttpCacheIntercept(httpCacheService));
    }

}

class RunTimeJs extends FullResponseIntercept {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(RunTimeJs.class);

    @Override
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {

        log.info("{}", httpRequest.headers());

        //在匹配到百度首页时插入js
//        return HttpUtil.checkUrl(pipeline.getHttpRequest(), "^www.baidu.com$") && HttpUtil.isHtml(httpRequest, httpResponse);
        boolean result = HttpUtil.checkUrl(pipeline.getHttpRequest(), "^(.*?)runtime.(.*?).js$");
        log.info("result: {}", result);
        return result;
    }

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        //打印原始响应信息
        log.info("resp: {}", httpResponse.toString());
        log.info("resp: {}", httpResponse.content().toString(Charset.defaultCharset()));
        //修改响应头和响应体
//        httpResponse.headers().set("handel", "edit head");
        log.info("content.class: {}", httpResponse.content().getClass());

        String str = "var e,t,n,r,o,i={},u={};";
        int index = ByteUtil.findText(httpResponse.content(), str);
        ByteUtil.insertText(httpResponse.content(), index, "window.U = u;window.I = i;");
        log.info("resp: {}", httpResponse.content().toString(Charset.defaultCharset()));


//        httpResponse.content().writeBytes("<script>alert('hello proxyee')</script>".getBytes());
    }

}

class HttpCacheIntercept extends FullResponseIntercept {
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
        boolean result = true;
//        if (url != null) {
//            // 域名
//            List<String> domainNames = new ArrayList<String>() {{
//                add("xhscdn.com");
//                add("xiaohongshu.com");
//                add("shop-vite");
//            }};
//            for (String domainName : domainNames) {
//                    if(url.matches("^(.*?)" + domainName + "(.*?)$")){
//                        result = true;
//                        break;
////                        return true;
//                    }
//            }
//
//            log.info("\n> > > > > > > > > > > > > > > > > > > > \nurl: {} {}\n", url, result);
//
//        }

        log.info("\n> > > > > > > > > > > > > > > > > > > > \nurl: {} {}\n", url, result);

        return result;
    }

    // 用于累积可能分块的请求体
    private final StringBuilder bodyContentBuilder = new StringBuilder();

    @Override
    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        HttpRequest request = pipeline.getHttpRequest();
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());

        log.info("< < < < < < < < < < < < < < < < < < < < url:{} {}\n", url, request.getClass());

        // 打印请求行信息
        HttpUtil.printRequest(request);

        if (httpRequest instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) httpRequest;
            ByteBuf contentBuf = httpContent.content();
            if (contentBuf.isReadable()) { // 检查缓冲区是否有可读字节
                String chunk = contentBuf.toString(CharsetUtil.UTF_8);
                bodyContentBuilder.append(chunk);
                log.info("BODY CHUNK: " + chunk); // 实时打印每个分块
            }

            // 判断是否是最后一块内容
            if (httpContent instanceof LastHttpContent) {
                String fullBody = bodyContentBuilder.toString();
                if (fullBody.isEmpty()) {
                    log.info("FULL BODY: (Empty)");
                } else {
                    log.info("FULL BODY: " + fullBody);
                }
                log.info("========== End of Request ==========\n");
                bodyContentBuilder.setLength(0); // 重置StringBuilder以备下一个请求
            }
        }

//        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
//        String context = httpResponse.content().toString(Charset.defaultCharset());
//        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} context:{}\n", url, httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE), context);


//        // 文件类型
//        List<String> fileTypes = new ArrayList<String>() {{
//            add("application/javascript");
////            add("application/xml");
//            add("text/javascript");
//            add("text/html; charset=utf-8");
//        }};
//        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
//        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} \n", url, httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE));
//
//        if (fileTypes.contains(httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE))) {
//            //  打印原始响应信息
//            String context = httpResponse.content().toString(Charset.defaultCharset());
//
//            HttpCache check = httpCacheService.check(new HttpCache(null, url, NettyUtil.httpHeadersToMap(httpRequest.headers()), NettyUtil.httpHeadersToMap(httpResponse.headers()), context, null, null));
//            if (null != check) {
//                ByteUtil.cover(httpResponse.content(), check.getResponseContent());
//            }
//        }
    }

}
