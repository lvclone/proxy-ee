/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.github.monkeywie.proxyee;

import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.test.XHSApiClient;
import com.github.monkeywie.proxyee.util.FileUtils;
import com.github.monkeywie.proxyee.util.transport.TransportConnectionThread;
import com.github.monkeywie.proxyee.util.transport.TransportWorker;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多数据源测试
 *
 * @author Mark sunlightcs@gmail.com
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class XiaoHongShu {
    private static final Logger logger = LoggerFactory.getLogger(XiaoHongShu.class);

    public static void main(String[] args) {
        String cursor = "";
//        String cursor = "67436233000000000703961e";
//        String cursor = "67348ec3000000001d03af5c";
        JSONObject jsonObject = new JSONObject();

        switch (cursor){
            case "":
                jsonObject.put("x-s", "XYW_eyJzaWduU3ZuIjoiNTYiLCJzaWduVHlwZSI6IngyIiwiYXBwSWQiOiJ4aHMtcGMtd2ViIiwic2lnblZlcnNpb24iOiIxIiwicGF5bG9hZCI6IjVjZmM1ZjYwYjZhMmViZGIzN2JlY2JlNzQ5NTgzOWUyMmRiMzJhNTM3YmRmMjVhMmRlZGMzYjFlYTFhYWQ5YTcxNzVhZDMwMjljMjg5YWY2MTIyMzg3ZTM0ZDZkN2I4MGQ1YjA5NGFlMWJhMGRkMWE2OGQzM2ZmOTJlZTgwM2QwMWIzOTc4NGQxN2E3YTA2ZmU0NjM0ZTE0NWQxYWZkOGZlNDU2Y2ZlYTk0NTM4MjUwZjUxYjU5NGU0NTI5NjFmM2E2ZTlkZWU2YzE1ZTAyMWUxYzBlZGZmZjMyMmY3ZWIxMTEyOGRiNmQwZDM4OTdiMzM3YjYxYTg5M2QxYjM0ZDAxMjAyOTA4MDJjMzc5YThjOGNjMmJmMTgwYzRkYTRiNzk5MDI4MDkzOTZlMmY4NTZmYWExZmZiNjlkNTZlOTRjZThhMTU4ZTg4NWI0ZGIxMDEyYjE1MGJlNzM3NzAyZWVlYTI5NjM3ZWRiZTY4YzVmOTEwM2NhMTJkOTU1MDMxNWJiZDY2NjBlYmM1Mjg4OWZjZmFjNGYxM2M2MGQ5MDlkIn0=");
//                jsonObject.put("x-s-common", "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");
                break;
            case "67436233000000000703961e":
                jsonObject.put("x-s", "XYW_eyJzaWduU3ZuIjoiNTYiLCJzaWduVHlwZSI6IngyIiwiYXBwSWQiOiJ4aHMtcGMtd2ViIiwic2lnblZlcnNpb24iOiIxIiwicGF5bG9hZCI6IjBjZTYxMzQyMDE1MzIxYTA3ZGE3Y2Y4MWNhZTJkODgzZTFiZmVlYWNlZGUyMmE2Njg4NTZlN2M0NGRlYzMzZmZkYWY1OWM0YTA1NWRlYzYxM2E1YzQ3Nzc3YjRkY2MwMThkYzY4NmViYzVkYTBkYjNkYjA0NDk4NTg4MTFiNDU0MTVmZjc1N2QwMDA5NTAyMTgwZjI0M2JlMjIyZmI2MzdmNmMzMTQ4ZDhkMzk4YmVjYzcyYWQ4OTAzMDZmOTM0MDMyYTRjMzJkNzk4Y2IzNDlhYTZjNDc4NmNjNWRlMDA3YTFmMGY5OWE4N2U5MzUwOTQ5MTJjZDc0YWFmMDZiZTQ3NDYzNDgyMTQ3ODExZTY2YTRiNWNhZTk1NGU5ZmIwOTFiYjljOWUwNmQ3MTQxNDhhYjUyNzYwNDEyM2M5MThmYTZjMzUzNDhhMzNmYTYxZTg4YjBkYjA0NDk4MWEzODVhYTFjYzZiZmJkMTgxYjUxNDdjN2FiNTk4MGI1MGQ3ZDdiMTc3NjhiOTAyMmZjYjE4ODU0ODdmNzE3MTliMWUxIn0=");
//                jsonObject.put("x-s-common", "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");
                break;
            case "67348ec3000000001d03af5c":
                jsonObject.put("x-s", "XYW_eyJzaWduU3ZuIjoiNTYiLCJzaWduVHlwZSI6IngyIiwiYXBwSWQiOiJ4aHMtcGMtd2ViIiwic2lnblZlcnNpb24iOiIxIiwicGF5bG9hZCI6ImQ4YjFjYjI4YWQwNTQ0NmQyZDQ3NGU4NWI3ZWY3NDBmNDlhYzhmMjY4MjNlNGEyOTc0NTNmZmIwNzY3MWYyMzhiNTMwZmMxMjcyMTBiNzNmYWZhNDU3NWZkOTRjYzViN2YzOTY5NTBhMTg5NjFlZmE4MDkyYzg3YjEzNmRkODIxN2ZlNTU2OTk5YThlMjVhNWIxZDM2YTAwYTQ1ZjFjMTg5MmVjZmM5NWNiOTZmNzM0NjY3NDE5MDkwM2ZhNjA4OTUzZWFiOGE0MWNlZDBlNDMwMDdiMTk4Yzg1M2UxZDQ5OWUxMjg1NjdhMmY5NTI4MWU0NTA5NzEyNWUxYjEyYmY3YWVjMThiNTJlZDgxNTkxYzRjNjc5YTVkODlhMmU0YWNkY2VjNmQ5M2JhMzI4MTgyNDc5YzQwYzIwYjgyNWFmZmViNTI0NDI2MjE4Yjk5MDRiYjNiY2I4NzhiNTlkYWVmMTA0NzY2MGRkNzM4OGY4MmRiYmY1M2Q4MWFmZWEyNGJhNGJjZjU5NTYwNTFjMTY4ZTIyMmI5ZjliYmMxNDRlIn0=");
                break;
        }
        XHSApiClient.page(cursor, jsonObject);
    }


    @Test
    public void index() throws InterruptedException {
//        System.out.println(FileUtil.exist("./mock/promotion/homepage-layout/home-list-all"));
        FileUtils.createTxt("./mock/promotion/homepage-layout/home-list-alls");

    }

    @Test
    public void asd() throws InterruptedException {

        TransportConnectionThread transportConnectionThread = new TransportConnectionThread();
        transportConnectionThread.run();
        TransportWorker.readQueue();
        logger.info("结束1");
        TransportWorker.page("");
        logger.info("结束");
        Thread.sleep(1000 * 60 * 1);
        logger.info("关闭");
    }

}
