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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.test.XHSApiClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

@Slf4j
public class TransportClient extends WebSocketClient {
//    String host = String.valueOf(SpringTool.getPropertiesValue("sonic.agent.host"));
//    String version = String.valueOf(SpringTool.getPropertiesValue("spring.version"));
//    Integer port = Integer.valueOf(SpringTool.getPropertiesValue("sonic.agent.port"));

    String uuid;

    String cursor;


    public TransportClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        TransportWorker.client = this;
        log.info("Connected and auth...");
    }

    @Override
    public void onMessage(String s) {
        JSONObject jsonObject = JSON.parseObject(s);

        log.info("Agent <- Server message: {}", jsonObject);
        TransportWorker.cachedThreadPool.execute(() -> {
            if(jsonObject.containsKey("sessionId")){
                uuid = jsonObject.getString("sessionId");
            } else if(jsonObject.containsKey("result")){
                String resultJson = jsonObject.getString("result");
                JSONObject xhsJson = new JSONObject();
                xhsJson.put("x-s", resultJson);
                TransportWorker.page(XHSApiClient.page(cursor, xhsJson));
            }

        });
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        if (TransportWorker.isKeyAuth) {
            log.info("Server disconnected. Retry in 10s...");
        }
        if (TransportWorker.client == this) {
            TransportWorker.client = null;
        }
    }

    @Override
    public void onError(Exception e) {
        log.info(e.getMessage());
    }

}
