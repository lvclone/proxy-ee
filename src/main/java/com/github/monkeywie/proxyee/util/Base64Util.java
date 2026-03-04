package com.github.monkeywie.proxyee.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @program: helium-pro-new
 * @ClassName Base64Utils
 * @description:
 * @author: Mr.Lv
 * @email: lvzhuozhuang@foxmail.com
 * @create: 2022-04-18 21:40
 * @Version 1.0
 **/
public class Base64Util {




    /**
     * String转换为Bse64
     * @param str
     * @return
     */
    public static String strConvertBase(String str) {
        if(null != str){
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(str.getBytes());
        }
        return null;
    }

    /**
     * Base64转换成String
     * @param str
     * @return
     */
    public static String baseConvertStr(String str) {
        if(null != str){
            Base64.Decoder decoder = Base64.getDecoder();
            try {
                return new String(decoder.decode(str.getBytes()), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }

    /**
     *
     */
    public static void main(String[] args) throws Exception {
        String str = "{\"height\":1547186,\"filter_modes\":[\"full\",\"light\",\"dataonly\"],\"before_block\":151317,\"before_address\":\"11Drbrzg9pNQZcGAghGWaMkHLcZDMwjdi36rrqiMfuP9En5wrP9\"}";
        str = "https://blog.csdn.net/qq_39763510/article/details/1391244630";

        //BASE64加密解密
        String data = strConvertBase(str);
//        String data = "Y2hlbmdkdXNpY2h1YW5jaGluYQ==";
        System.out.println("sun.misc.BASE64 加密后：" + data);


//        data = "eyJoZWlnaHQiOjE1MzM3OTcsImZpbHRlcl9tb2RlcyI6WyJmdWxsIiwibGlnaHQiLCJkYXRhb25seSJdLCJiZWZvcmVfYmxvY2siOjE1MTMxNywiYmVmb3JlX2FkZHJlc3MiOiIxMURyYnJ6ZzlwTlFaY0dBZ2hHV2FNa0hMY1pETXdqZGkzNnJycWlNZnVQOUVuNXdyUDkifQ";
//        data = "eyJoZWlnaHQiOjE1MzM3NjAsImZpbHRlcl9tb2RlcyI6WyJmdWxsIiwibGlnaHQiLCJkYXRhb25seSJdLCJiZWZvcmVfYmxvY2siOjExNTA2NDksImJlZm9yZV9hZGRyZXNzIjoiMTFxS2Z0dlB1dmptck1Xd3UxRVB0cXdQMTJGVzhTZnl4Zmd0eER0VW5OWEh6ZVlRSnlCIn0";
        System.out.println("sun.misc.BASE64 解密后：" + baseConvertStr(data));
    }

}
