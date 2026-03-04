package com.github.monkeywie.proxyee.util.github;

import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.util.ObjectUtils;

public class CharUtil {

    public static JSONObject getHeadres(String headresStr) {
        headresStr = headresStr.trim();
//        Map<String, String> result = new HashMap<>();

        JSONObject result = new JSONObject();

        if (ObjectUtils.isEmpty(headresStr)) {
            return result;
        }
        String[] headers = headresStr.split("\n");
        for (String header : headers) {
            if (ObjectUtils.notIsEmpty(header)) {
                String[] aa = header.split(":");
                if (aa.length == 2) {
                    if (ObjectUtils.isEmpty(aa[0]) || ObjectUtils.isEmpty(aa[1])) {
                        continue;
                    }
                    result.put(aa[0], aa[1]);
                }
            }
        }
        return result;
    }

}
