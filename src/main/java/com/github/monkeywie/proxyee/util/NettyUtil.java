package com.github.monkeywie.proxyee.util;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class NettyUtil {

    public static Map<String, String> httpHeadersToMap(HttpHeaders headers) {
        Map<String, String> map = new HashMap<>();

        if (headers != null) {
            headers.forEach((v) -> {
                map.put(v.getKey(), v.getValue());
            });
        }
        return map;
    }


    public static HttpHeaders mapToHttpHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }
        return httpHeaders;
    }
}
