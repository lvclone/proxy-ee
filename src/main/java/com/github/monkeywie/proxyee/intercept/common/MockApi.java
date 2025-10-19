package com.github.monkeywie.proxyee.intercept.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.intercept.HttpProxyIntercept;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.util.ByteUtil;
import com.github.monkeywie.proxyee.util.FileUtils;
import com.github.monkeywie.proxyee.util.KKResult;
import com.github.monkeywie.proxyee.util.ProtoUtil.RequestProto;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import java.io.File;

/**
 * MockApi Api
 */
@Slf4j
public class MockApi extends HttpProxyIntercept {

    private boolean isDirect = false;

    @Override
    public void beforeRequest(Channel clientChannel, HttpRequest httpRequest,
                              HttpProxyInterceptPipeline pipeline) throws Exception {
        RequestProto requestProto = pipeline.getRequestProto();
        if (!requestProto.getProxy()) {
            isDirect = true;

            String apiPath = httpRequest.uri();
            String apiPara = null;
            if (apiPath.contains("?")) {
                String[] split = httpRequest.uri().split("\\?");
                apiPath = split[0];
                apiPara = split[1];
            }
            log.info("apiPath: {} apiPara: {} headers: {} \n", apiPath, apiPara, httpRequest.headers());

            String path = "./mock" + apiPath;
            if (new CertDownIntercept().beforeRequest(clientChannel, httpRequest))
                return;

            String contentType = "application/json; charset=utf-8";
            Object connection = HttpHeaderValues.KEEP_ALIVE;
            byte[] bytes = null;

            // 文件配置Mock API
            if (new File(path).exists()) {
                String jsonStr = FileUtils.readLine(path);
                JSON json = "{".equals(jsonStr.substring(0, 1)) ?
                        JSONObject.parseObject(jsonStr) : JSONObject.parseArray(jsonStr);

                bytes = json.toJSONString().getBytes();
            }

            // 不存在接口
            if (null == bytes) {
                log.info("bytes is null");
                bytes = JSONObject.toJSONString(KKResult.fail("不存在接口！！")).getBytes();
            }
            ByteUtil.buildContext(clientChannel, contentType, connection, bytes);

        } else {
            pipeline.beforeRequest(clientChannel, httpRequest);
        }
    }

    @Override
    public void beforeRequest(Channel clientChannel, HttpContent httpContent,
                              HttpProxyInterceptPipeline pipeline) throws Exception {
        if (!isDirect) {
            pipeline.beforeRequest(clientChannel, httpContent);
        }
    }
}
