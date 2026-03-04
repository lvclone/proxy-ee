/*
 *   sonic-agent  Agent of Sonic Cloud Real Machine Platform.
 *   Copyright (C) 2022 SonicCloudOrg
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.monkeywie.proxyee.util.transport;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class TransportWorker {
    private static LinkedBlockingQueue<JSONObject> dataQueue = new LinkedBlockingQueue<>();
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public static TransportClient client = null;
    public static Boolean isKeyAuth = true;

    static String userId = "5a177936e8ac2b535e9441b3";


    public static void send(JSONObject jsonObject) {
        dataQueue.offer(jsonObject);
    }

    public static void page(String cursor) {
        String parameter = String.format("/api/sns/web/v2/note/collect/page?num=30&cursor=%s&user_id=%s&image_formats=jpg,webp,avif&xsec_token=&xsec_source=", cursor, userId);

        String js = "{\n" +
                "            \"header\": {\n" +
                "                \"op\": \"admin\",\n" +
                "                \"toSessionId\": \"" + client.uuid + "\"\n" +
                "            },\n" +
                "            \"data\": {\n" +
                "                \"operate\": \"webmsxyw\",\n" +
                "                \"parameter\": \"" + parameter + "\"\n" +
                "            }\n" +
                "        }";


        if (cursor.equals("67348ec3000000001d03af5c")) {
            log.info("到达指定");
            return;
        }
        client.cursor = cursor;
        TransportWorker.send(JSONObject.parseObject(js));
    }

    public static void readQueue() {
        cachedThreadPool.execute(() -> {
            while (isKeyAuth) {
                try {
                    if (client != null && client.isOpen()) {
                        if (!dataQueue.isEmpty()) {
                            JSONObject m = dataQueue.poll();
                            client.send(m.toJSONString());
                        } else {
                            log.info("休息1000");
                            Thread.sleep(1000);
                        }
                    } else {
                        log.info("休息5000");
                        Thread.sleep(5000);
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        });
    }
}
