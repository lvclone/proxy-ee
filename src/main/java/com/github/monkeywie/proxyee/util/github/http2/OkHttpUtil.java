package com.github.monkeywie.proxyee.util.github.http2;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.monkeywie.proxyee.util.BeanUtils;
import com.github.monkeywie.proxyee.util.github.CacheUtil;
import com.github.monkeywie.proxyee.util.github.CharUtil;
import com.github.monkeywie.proxyee.util.github.http2.interceptor.ResponseCompressionInterceptor;
//import com.kkmh.infrastructure.util.BeanUtils;
import okhttp3.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: 基于OKHttp的请求工具类</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: NewLand Computer</p>
 *
 * @author linds
 * @version 1.0 developer 2020/7/2 created
 */
public class OkHttpUtil {

//    private static final Logger logger = LoggerFactory.getLogger(com.utils.http2.OkHttpUtil.class);

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8");

    private static final byte[] LOCKER = new byte[0];

    private static OkHttpUtil instance;
    private final OkHttpClient mOkHttpClient;

    private final boolean isProxy = true;

    private final boolean userCache = false;
    private CacheUtil cacheUtil = null;

    private OkHttpUtil(int readTimeout, int connectTimeout, int writeTimeout) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //设置读取超时时间
        clientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);
        //设置超时连接时间
        clientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        //设置写入超时时间
        clientBuilder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        //设置SSL
        SSL.setSSLSocketFactory(clientBuilder);

        clientBuilder.hostnameVerifier((hostname, session) -> Boolean.TRUE);

        if (isProxy) {
            // 假设代理服务器地址为localhost，端口为8888
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 9999));
            clientBuilder.proxy(proxy);
        }

        if (userCache) {
            cacheUtil = new CacheUtil("httpRequestBody");
        }
        clientBuilder.addInterceptor(new ResponseCompressionInterceptor());

        mOkHttpClient = clientBuilder.build();
    }

    /**
     * 单例模式获取NetUtils
     */
    public static OkHttpUtil getInstance(int readTimeout, int connectTimeout, int writeTimeout) {
        if (instance == null) {
            synchronized (OkHttpUtil.class) {
                if (Objects.isNull(instance)) {
                    instance = new OkHttpUtil(readTimeout, connectTimeout, writeTimeout);
                }
            }
        }
        return instance;
    }

    /**
     * 针对json post处理
     */
    public String postJson(String url, String json, Map<String, String> headerMap) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        return post(url, headerMap, body);
    }


    /**
     * 针对json post处理
     */
    public String postxML(String url, String xmlStr, Map<String, String> headerMap) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_XML, xmlStr);
        return post(url, headerMap, body);
    }

    //    @NotNull
    private String post(String url, Map<String, String> headerMap, RequestBody body) {
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
//        addHeaders(headerMap, requestBuilder);
        return send(requestBuilder, headerMap);
    }

    public JSONObject sendToJSON(Request.Builder requestBuilder, Object headerO) {
        String result = send(requestBuilder, headerO);
        if (result != null) {
            return JSONObject.parseObject(result);
        } else {
            return new JSONObject();
        }
    }

    private String genKey(Request request) {
        return String.format("%s", request.url().url().toString().replaceAll("[:/,?]","_"));
    }

    public String send(Request.Builder requestBuilder, Object headerO) {
        addHeaders(headerO, requestBuilder);
        // 删除没必要的头部信息
        headerKeys.forEach(requestBuilder::removeHeader);

        Request request = requestBuilder.build();
        System.out.println(request.url().url() + "\n> > > > > > > > > > > > > > > > > > > > > > > > > >");
        String result;
        if (userCache) {
            // 读取缓存
            result = cacheUtil.readResult(genKey(request));
            if (result != null && !result.isEmpty()) {
                return result;
            }
        }
        System.out.println("发送请求～");
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
                System.out.println("< < < < < < < < < < < < < < < < < < < < < < < < < <\n" + result);
                if (userCache) {
                    // 保存缓存
                    cacheUtil.saveResult(genKey(request), result);
                }
                return result;
            } else {
                System.out.println("MsgException:code");
                result = Objects.requireNonNull(response.body()).string();
                System.out.println(result);
//                throw new MsgException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 添加header信息
     */
    private static void addHeaders(Object headerO, Request.Builder builder) {
        Map<String, String> headerMap = new HashMap<String, String>() {{
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");
        }};
        if (headerO != null) {
            JSONObject headerJSON = null;
            if (headerO instanceof String)
                headerJSON = CharUtil.getHeadres((String) headerO);
            else if (headerO instanceof JSONObject) {
                headerJSON = (JSONObject) headerO;
            }

            System.out.println(headerJSON.toJSONString());
            if(null != headerJSON && !headerJSON.isEmpty()) {
                headerMap.putAll(BeanUtils.toJavaObject(headerJSON, new TypeReference<Map<String, String>>() {
                }));
            }

        }

        headerMap.forEach(builder::addHeader);
    }


    List<String> headerKeys = new ArrayList<String>() {
        {
            //
            // builder.removeHeader("User-Agent");
            add("content-length");
            add("Content-Length");
            add(":method");
            add("Authorization");
            add("Host");


//            add(":authority");
//            add(":path");
//            add(":scheme");
        }
    };

//    public JSONObject send(Request.Builder builder) {
//
//        String headStr = "Host:edith.xiaohongshu.com\n" +
//                "//User-Agent:Fiddler-Everywhere\n" +
//                "sec-ch-ua-platform:\"macOS\"\n" +
//                "sec-ch-ua:\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"\n" +
//                "x-xray-traceid:c97ec6dfa6a516343bb9b8c8a5081cfe\n" +
//                "sec-ch-ua-mobile:?0\n" +
//                "x-t:1730830778191\n" +
//                "x-b3-traceid:f2161d42c9c783c9\n" +
//                "x-s-common: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\n" +
//                "user-agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36\n" +
//                "accept:application/json, text/plain, */*\n" +
//                "x-s:XYW_eyJzaWduU3ZuIjoiNTUiLCJzaWduVHlwZSI6IngyIiwiYXBwSWQiOiJ4aHMtcGMtd2ViIiwic2lnblZlcnNpb24iOiIxIiwicGF5bG9hZCI6IjVhMjlkZDFiMDQ5MzlmM2E2NTBiMzM4MTg4ZDAzZTA5YzA3NGI1MTA5NTkyOWUxZmQ1MTE4ZjIzZjkxYWUzMjgyMTljNmNhMDg1YjY4MDQ4OGM1MDc3YmI4M2Y4Mjc1MDk4NTFkMzAxNDg2MDUzYmQxOTI3MWNjNzUzYWU1NGYwOTVlZjM3Y2MxYjRmYjgwY2IwNWExYzZlOGUwZjM0Zjc0NDY5ZTRjODcwY2NiNzU4ZTA4YWU5ZmQxZjY3MjdkOThiYWYyYTBiMzczOGE5ZDczMzgzY2NhNjJiNDFiODk3Njc1NWEyOWEyNWQxYjBhNGIwNDFjZGRhZTg0N2Q1OWNhYjJmZDE0NTI3ZTk5NWI3M2U1OWEwNDI5MTJmZjYwYjZjY2U4ZjRhZTg4OWU3MWQzMTE1M2JmY2MxMTBhMmUxYTRhNTQ0ZWVmNDliNzAwZWFkMzUyYjBhNWViZDBkYjc0NjYyODFiYmYzM2E4NjdmODgxOTk5MDM3OWFhMjI3ZWZiOTg0ZTZlMzkxNzBlOTM3M2RkNGQwM2U2YjNjMmI1In0=\n" +
//                "origin:https://www.xiaohongshu.com\n" +
//                "sec-fetch-site:same-site\n" +
//                "sec-fetch-mode:cors\n" +
//                "sec-fetch-dest:empty\n" +
//                "referer:https://www.xiaohongshu.com/\n" +
//                "accept-encoding:gzip, deflate, br, zstd\n" +
//                "accept-language:en,zh-CN;q=0.9,zh;q=0.8\n" +
//                "priority:u=1, i\n" +
//                "cookie:gid=yY0ySjWW8KYjyY0ySjWKqi1v4YVqSWWTl4q4IjU9VKyIKFq8qx2JqA888jyYj8J8SD4JdfYf; abRequestId=c1d099ec-875c-5db3-b08f-d0c5c0c44e3f; a1=192fd08cbefsslrmh2l8kooc4ogfnwzcdgbg4t77330000106408; webId=8ac1d8f8d320f3337240ea2d65a2779a; web_session=040069768e88b16411e52c181f354b17067630; customer-sso-sid=68c517433843732345462753dd9011d16a2b1132; x-user-id-creator.xiaohongshu.com=5a177936e8ac2b535e9441b3; customerClientId=499592909421768; access-token-creator.xiaohongshu.com=customer.creator.AT-68c517433843732344105443adqozz7fwwpf70fw; galaxy_creator_session_id=RDErIxGMu5KWIIJ2bnPFjgUZ0J2bt44ZB2US; galaxy.creator.beaker.session.id=1730826621326073680496; xsecappid=xhs-pc-web; webBuild=4.41.1; acw_tc=62ccb90b3cc8f0573d3838591c3097405919661f5a1489013d642eeb27f71535; websectiga=29098a4cf41f76ee3f8db19051aaa60c0fc7c5e305572fec762da32d457d76ae; sec_poison_id=aa44f449-7b75-451d-b657-102ec8da4d66";
//
//        addHeaders(headStr, builder);
//
//        // 删除没必要的头部信息
//        headerKeys.forEach(builder::removeHeader);
//
//        Request request = builder.build();
//        JSONObject json = new JSONObject();
//
//        try (Response response = mOkHttpClient.newCall(request).execute()) {
//            if (response.isSuccessful()) {
//                String result = Objects.requireNonNull(response.body()).string();
//
//                return result;
////                System.out.println("log:\n" + result);
////                json = JSONObject.parseObject(result);
////
////                return json;
//            }
//        } catch (MsgException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }


}
