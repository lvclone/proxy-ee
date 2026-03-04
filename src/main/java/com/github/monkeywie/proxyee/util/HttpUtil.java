package com.github.monkeywie.proxyee.util;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {

    public static String buildUrl(HttpRequest httpRequest){
        String host = httpRequest.headers().get(HttpHeaderNames.HOST);
        if (host != null) {
            String url;
            if (httpRequest.uri().indexOf("/") == 0) {
                if (httpRequest.uri().length() > 1) {
                    url = host + httpRequest.uri();
                } else {
                    url = host;
                }
            } else {
                url = httpRequest.uri();
            }
            return url;
        }
        return null;
    }

    /**
     * 检测url是否匹配
     */
    public static boolean checkUrl(HttpRequest httpRequest, String regex) {
        String url = buildUrl(httpRequest);

        if (url != null && regex != null) {
            return url.matches(regex);
        }
        return false;
    }

    /**
     * 检测头中的值是否为预期
     *
     * @param httpHeaders
     * @param name
     * @param regex
     * @return
     */
    public static boolean checkHeader(HttpHeaders httpHeaders, AsciiString name, String regex) {
        String s = httpHeaders.get(name);
        return s != null && s.matches(regex);
    }

    /**
     * 检测是否为请求网页资源
     */
    public static boolean isHtml(HttpRequest httpRequest, HttpResponse httpResponse) {
        String accept = httpRequest.headers().get(HttpHeaderNames.ACCEPT);
        String contentType = httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE);
        return httpResponse.status().code() == 200 && accept != null && accept
                .matches("^.*text/html.*$") && contentType != null && contentType
                .matches("^text/html.*$");
    }

    public static void printRequest(HttpRequest request) {
        // 打印请求方法
        log.info("METHOD: " + request.method().toString());

        // 打印完整URI
        String uri = request.uri();
        log.info("FULL URI: " + uri);

        // 解析并打印路径和查询参数
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        log.info("PATH: " + queryStringDecoder.path());
        Map<String, List<String>> params = queryStringDecoder.parameters();
        if (!params.isEmpty()) {
            Map<String,String> queryMap = new HashMap<>();
            for (Map.Entry<String, List<String>> param : params.entrySet()) {
                queryMap.put(param.getKey(), param.getValue().get(0));
            }
            log.info("queryMap: {}", JSON.toJSONString(queryMap));
        }

        // 打印请求头
        HttpHeaders headers = request.headers();
        if (!headers.isEmpty()) {
            Map<String,String> headersMap = new HashMap<>();
            for (Map.Entry<String, String> header : headers.entries()) {
                headersMap.put(header.getKey(), header.getValue());
            }
            log.info("HEADER: {}", JSON.toJSONString(headersMap));
        }

    }



}
