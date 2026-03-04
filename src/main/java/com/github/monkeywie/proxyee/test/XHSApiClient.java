package com.github.monkeywie.proxyee.test;

import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.util.JSONUtils;
import com.github.monkeywie.proxyee.util.github.http2.OkHttpUtil;
import com.github.monkeywie.proxyee.util.github.packetCapture.HtmlUtil;
import com.github.monkeywie.proxyee.util.github.packetCapture.WebmagicUtil;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Request;
import org.junit.Test;

import java.util.Map;

public class XHSApiClient {

    OkHttpUtil okHttpUtil = OkHttpUtil.getInstance(10, 10, 10);

    /*
        BASE_URL 设置为你自己的网易云音乐API地址
     */
    private static final String BASE_URL = "https://edith.xiaohongshu.com/api";
    private static final String API_URL = BASE_URL + "/sns/web/";

    //    private final String BASE_URL = "https://edith.xiaohongshu.com/api";
//    private final String API_URL = BASE_URL + "/sns/web/";
    private final String WEBSITE_URL = "https://www.xiaohongshu.com/";
    private final JSONObject INITIAL_STATE = new JSONObject();

    //https://edith.xiaohongshu.com/api/sns/web/v2/note/collect/page
    // ?num=30&cursor=&user_id=5a177936e8ac2b535e9441b3&image_formats=jpg,webp,avif&xsec_token=&xsec_source=

    /**
     * 收集
     *
     * @param cursor eg: 6624e7e100000000010332f2
     * @param userId eg: 5a177936e8ac2b535e9441b3
     * @return json
     */
    public JSONObject noteCollect(String cursor, String xXrayTraceid, String xB3Traceid, String xS, String xSCommon, long xT) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x-xray-traceid", xXrayTraceid);
        jsonObject.put("x-b3-traceid", xB3Traceid);
        jsonObject.put("x-s", xS);
        jsonObject.put("x-s-common", xSCommon);
        jsonObject.put("x-t", xT);


        return noteCollect(cursor, jsonObject);
    }

    public static String page(String cursor, JSONObject sub) {
        XHSApiClient xhsClient = new XHSApiClient();
        xhsClient.setUserId("5a177936e8ac2b535e9441b3");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x-xray-traceid", "c9b85c9fe4bef48e1d0de87a6f57b86a");
        jsonObject.put("x-b3-traceid", "55d600ba8c884abe");
        jsonObject.put("x-t", 1732121950810L);
        jsonObject.put("x-s-common", "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");

        jsonObject.putAll(sub);
        JSONObject result = xhsClient.noteCollect(cursor, jsonObject);

//        cursor = xhsApiClient.noteCollect((String) JSONUtils.jsGetData(cursor, "data.cursor"));
        System.out.println(result);

        String cursorRe = (String) JSONUtils.jsGetData(result, "data.cursor");

        System.out.println(cursorRe);

        return cursorRe;
    }


    public JSONObject noteCollect(String cursor, JSONObject jsonObject) {
        String url = API_URL + "v2/note/collect/page?num=30&cursor=" + cursor + "&user_id=" + getUserId() + "&image_formats=jpg,webp,avif&xsec_token=&xsec_source=";
//        String url = API_URL + "v2/note/collect/page?num=30&cursor=65086d3a00000000150085fd&user_id=5a177936e8ac2b535e9441b3&image_formats=jpg,webp,avif&xsec_token=&xsec_source=";
//        String url = API_URL + "v2/note/collect/page?num=30&cursor=&user_id=5a177936e8ac2b535e9441b3&image_formats=jpg,webp,avif&xsec_token=&xsec_source=";

        JSONObject parse = new JSONObject();
        parse.put("accept", "application/json, text/plain, */*");
        parse.put("accept-language", "en,zh-CN;q=0.9,zh;q=0.8");
        parse.put("sec-ch-ua", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"");
        parse.put("sec-ch-ua-mobile", "?0");
        parse.put("sec-ch-ua-platform", "\"macOS\"");
        parse.put("sec-fetch-dest", "empty");
        parse.put("sec-fetch-mode", "cors");
        parse.put("sec-fetch-site", "same-site");
        parse.put("cookie", "abRequestId=6e8e00c0-92b5-53fa-a563-d382cdd75b57; a1=19348cbe608kafljfz0aiyqop4m54u122sz0vrsgd30000408389; webId=7a59008258e7622b12b9e2deb189c8e3; gid=yjq4YSDi4iJiyjq4YSDdKMFM8YT0i1Viu803ukfvAlU468q8247yJh88848YqYj8ySJ0ySfq; xsecappid=xhs-pc-web; web_session=040069768e88b16411e50b3074354bf06b83e7; webBuild=4.44.1; unread={%22ub%22:%2267418e24000000000202f1db%22%2C%22ue%22:%2267371b74000000001a037a2c%22%2C%22uc%22:14}; websectiga=59d3ef1e60c4aa37a7df3c23467bd46d7f1da0b1918cf335ee7f2e9e52ac04cf; sec_poison_id=754e4538-030d-4187-98b0-57b694e837a7; acw_tc=0ad598a217327199040904189e5bca435f766c4f2c842dbb481752c6b2ae6a");
        parse.put("Referer", "https://www.xiaohongshu.com/");
        parse.put("Referrer-Policy", "strict-origin-when-cross-origin");

        parse.putAll(jsonObject);
        return okHttpUtil.sendToJSON(new Request.Builder().url(url), parse);
    }

    /**
     * 专辑
     *
     * @param cursor
     * @return
     */
    public JSONObject album(int page) {
        String url = API_URL + "v1/board/user?user_id=" + getUserId() + "&page=" + page + "&num=30";
        // https://edith.xiaohongshu.com/api/sns/web/v1/board/user
        // ?user_id=5a177936e8ac2b535e9441b3&page=1&num=30&image_formats=jpg,webp,avif&xsec_token=&xsec_source=
        return sendApi(url, headStr);
    }

    /**
     * 获取参数
     *
     * @param parameters 参数
     * @return data
     */
    public Object getValue(Parameters parameters) {
        if (!INITIAL_STATE.containsKey(parameters.getJsonKey())) {
            switch (parameters.getJsonKey()) {
                case "explore":
                    parseHtml(explore(), "explore");
                    break;
                case "userProfile":
                    parseHtml(userProfile(), "userProfile");
                    break;
            }
        }
        return JSONUtils.jsGetData(INITIAL_STATE.getJSONObject(parameters.getJsonKey()), parameters.getPath());
    }

    /**
     * 首页
     *
     * @return html
     */
    public String explore() {
        String url = WEBSITE_URL + "explore";
        String html = okHttpUtil.send(new Request.Builder().url(url), headStr);
        if (null == html) {
            throw new IllegalArgumentException("user_id is null");
        }
        return html;
    }

    /**
     * 用户配置文件
     *
     * @return html
     */
    public String userProfile() {
        if (null == getUserId()) {
            throw new IllegalArgumentException("user_id is null");
        }
        String url = WEBSITE_URL + "user/profile/" + getUserId() + "?tab=fav&subTab=note";
        String html = okHttpUtil.send(new Request.Builder().url(url), headStr);
        if (null == html) {
            throw new IllegalArgumentException("html is null");
        }
        return html;
    }

    @Getter
    @Setter
    String headStr = null;

    @Setter
    String userId = null;

    public String getUserId() {
        if (null == userId) {
            userId = (String) getValue(Parameters.userId);
            if (null == userId) {
                throw new IllegalArgumentException("user_id is null");
            }
        }
        return userId;
    }

    @Setter
    public String startCursor;

    public String getStartCursor() {
        if (null == startCursor) {
            setStartCursor((String) getValue(Parameters.startCursor));
        }
        return startCursor;
    }


    JSONObject sendApi(String url, String header) {
//        return okHttpUtil.sendToJSON(new Request.Builder().url(generalParameters(url)), headStr);
        return okHttpUtil.sendToJSON(new Request.Builder().url(url), header);
    }

//    JSONObject sendApi(String url, Map<String, Object> params) {
//
//        // 初始化请求的URL
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
//
//        generalParameters(params);
//
//        // 添加查询参数
//        params.keySet().forEach(key -> urlBuilder.addQueryParameter(key, String.valueOf(params.get(key))));
//
//        return sendApi(urlBuilder.build().toString());
//    }

    void generalParameters(Map<String, Object> params) {
        params.put("user_id", getUserId());
        params.put("image_formats", "jpg,webp,avif");
        params.put("xsec_token", "");
        params.put("xsec_source", "");
    }

    public String generalParameters(String url) {
        return API_URL + url + "&image_formats=jpg,webp,avif&xsec_token=&xsec_source=";
    }

    void parseHtml(String html, String key) {
        String jsData = WebmagicUtil.getJsData(html, "window.__INITIAL_STATE__=", "<");
        System.out.println("jsData: " + jsData);
        Object result = HtmlUtil.parseJSDatatoJson(jsData, "__INITIAL_STATE__");
        JSONObject jsonObject = JSONUtils.toJSONObject(result);
        INITIAL_STATE.put(key, jsonObject);
    }

    // 文本文件
    enum Parameters {
        userId("userId", "explore", "user.userInfo.user_id"),
        startCursor("startCursor", "userProfile", "user.noteQueries.1.cursor");

        @Getter
        private final String key;

        @Getter
        private final String jsonKey;

        @Getter
        private final String path;

        private Class<?> validatorGroupClass;

        Parameters(String key, String jsonKey, String path) {
            this.key = key;
            this.jsonKey = jsonKey;
            this.path = path;
        }
    }
//    public void parseHtml() throws ScriptException {
//
//        String responseText = readResult();
//        Page page = new Page();
//        page.setRequest(new us.codecraft.webmagic.Request("http://my.oschina.net/flashsword/blog"));
//        page.setUrl(new PlainText("http://my.oschina.net/flashsword/blog"));
//        page.setHtml(new Html(responseText));
//
//        String cursor = null;
//        Object jsonObject = HtmlUtil.parseJSDatatoJson(WebmagicUtil.getJsData(page.getHtml(), "window.__INITIAL_STATE__=", "<"), "__INITIAL_STATE__");
//        cursor = (String) JSONUtils.jsGetData(JSONUtils.toJSONObject(jsonObject), "user.noteQueries.1.cursor");
//        System.out.println(cursor);
//
//    }

    @Test
    public void asd() {
        System.out.println(okHttpUtil.send(new Request.Builder().url("https://splidejs.com/webpack-runtime-654f5c8e2fdb99bf659e.js"), headStr));
        System.out.println(okHttpUtil.send(new Request.Builder().url("https://splidejs.com/webpack-runtime-654f5c8e2fdb99bf659e.js"), headStr));
        System.out.println(okHttpUtil.send(new Request.Builder().url("https://splidejs.com/webpack-runtime-654f5c8e2fdb99bf659e.js"), headStr));
    }
}
