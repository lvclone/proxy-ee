package com.github.monkeywie.proxyee.util.github.packetCapture;

import us.codecraft.webmagic.selector.Html;

public class WebmagicUtil {


    /**
     * @param html     ....
     * @param startStr eg: window.__INITIAL_STATE__=
     * @param endStr   eg: <
     * @return
     */
    public static String getJsData(String html, String startStr, String endStr) {
//        String regex = "window.__INITIAL_STATE__ = (\\{.+?\\});";
//        String regex = "window.__INITIAL_STATE__ =(.*?)</script>";
        String regex = startStr + "(.*?)" + endStr;
        return new Html(html).xpath("//script/text()/regex('" + regex + "')").get();
    }
}
