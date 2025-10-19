package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.util.ByteUtil;
import com.github.monkeywie.proxyee.util.FileUtils;
import com.github.monkeywie.proxyee.util.HttpUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.monkeywie.proxyee.intercept.common.pictureRecording.PictureRecording.getFilePath;
import static com.github.monkeywie.proxyee.intercept.common.pictureRecording.PictureRecording.getHeaderFilePath;

public class PictureRecordingCacheUtil {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(PictureRecordingCacheUtil.class);

    public void beforeRequest(Channel clientChannel, HttpRequest httpRequest, HttpProxyInterceptPipeline pipeline) throws Exception {
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        if (null != url) {
            String filePath = url.contains("?") ? url.split("\\?")[0] : url;
            int index = url.indexOf("/");
            if (index == -1) {
                filePath = filePath + "/index.html";
            }
//            String contentType = null;
//            Object connection = null;
            byte[] bytes = null;

            if (filePath.contains(".")) {
                String[] split = filePath.split("\\.");
                String fileType = split[split.length - 1];
//                contentType = switch (fileType) {
//                    case "html" -> "text/html";
//                    case "jpg", "jpeg" -> "image/jpeg";
//                    case "webp" -> "image/webp";
//                    case "png" -> "image/png";
//                    case "woff2" -> "font/woff2";
//                    case "js" -> "application/javascript";
//                    case "json" -> "application/json";
//                    default -> contentType;
//                };
//
//                connection = switch (fileType) {
//                    case "json", "html", "js",
//                         "webp", "jpg", "webmanifest", "png" -> HttpHeaderValues.KEEP_ALIVE;
//                    default -> connection;
//                };

                switch (fileType) {
                    case "json":
                    case "html":
                    case "js":
                        bytes = FileUtils.readLine(getFilePath(filePath)).getBytes();
                        break;
                    case "jpg":
                    case "png":
                    case "webp":
                        bytes = IoUtil.readBytes(Files.newInputStream(Paths.get(getFilePath(filePath))));
                        break;
                    case "webmanifest":
                        bytes = null;
                }
                String headerFilePath = getHeaderFilePath(filePath);

                if (null != bytes && new File(headerFilePath).exists()) {
                    JSONObject jsonObject = null;
                    if (fileType.equals("html")) {
                        jsonObject = JSON.parseObject("{\"Connection\":\"keep-alive\",\"Vary\":\"Accept-Encoding\",\"Content-Type\":\"text/html\"}");
                    } else {
                        jsonObject = JSONObject.parseObject(FileUtils.readLine(headerFilePath));
                    }

                    ByteUtil.buildContext(clientChannel, jsonObject, bytes);
                    return;
                }
            }
        }


        pipeline.beforeRequest(clientChannel, httpRequest);
//        pipeline.beforeRequest(clientChannel, httpRequest);

//        Server: nginx
//        Date: Wed, 08 Jan 2025 05:19:55 GMT
//        Content-Type: text/html
//        Connection: keep-alive
//        Vary: Accept-Encoding
//        Last-Modified: Fri, 25 Nov 2022 07:04:56 GMT
//        HttpCacheInterface-Control: max-age=1
//        Expires: Wed, 08 Jan 2025 05:19:56 GMT
//        content-length: 281494
    }

    public void handleResponse(FullHttpResponse httpResponse, String url) {
        String contentType = httpResponse.headers().get("content-type");
        if (null == contentType) {
            log.info("contentType not null headers:{}", httpResponse.headers());
            return;
        }

        String filePath = url.contains("?") ? url.split("\\?")[0] : url;
        filePath = !url.contains("/") ? filePath + "/index.html" : filePath;

        String fileSavePath = getFilePath(filePath);
        if (!new File(fileSavePath).exists()) {
            log.info("url:{} contentType:{} head:{}", url, contentType, httpResponse.headers());

            if (contentType.startsWith("image/")) {
                FileUtils.saveImage(httpResponse.content(), fileSavePath);
            } else {
                FileUtils.write(fileSavePath, httpResponse.content().toString(Charset.defaultCharset()));
            }

            JSONObject headers = new JSONObject();
            httpResponse.headers().entries().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                headers.put(key, value);
            });
//            getHeaderFilePath
            FileUtils.write(getHeaderFilePath(filePath), headers.toJSONString());
        }
    }

    public static String getPath(String url) {
        int index = url.lastIndexOf("/");
        String fileName;
        if (index > -1) {
            // .replaceAll("\\.(.*?)\\.", ".")
            fileName = url.substring(index + 1);
            index = fileName.indexOf(".");
            if (index == -1) {
                fileName = fileName + ".txt";
            }
        } else {
            fileName = String.format("%s", url.replaceAll("/", "_"));
        }
//        return String.format("%s", url);
        return fileName.replaceAll("\\.(.*?)\\.", ".");
    }

    /**
     * 保存文本
     *
     * @param path
     * @param result
     */
    public void saveResult(String path, String result) {
        FileUtils.write(getFilePath(path), result);
    }

    /**
     * 读取文本
     *
     * @param path
     * @return
     */
    public String readResult(String path) {
        File file = new File(getFilePath(path));
        if (file.exists()) {
            return FileUtils.readLine(getFilePath(path));
        } else {
            return null;
        }

    }


    public static void main(String[] args) {
        PictureRecordingCacheUtil cacheUtil = new PictureRecordingCacheUtil();
        System.out.println(cacheUtil.getPath("https://fe-static.xhscdn.com/formula-static/xhs-pc-web/public/js/runtime.fa8d6cd.js"));
    }

}
