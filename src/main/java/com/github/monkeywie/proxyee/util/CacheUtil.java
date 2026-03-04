package com.github.monkeywie.proxyee.util;

import com.aayushatharva.brotli4j.decoder.Decoder;
import com.aayushatharva.brotli4j.decoder.DirectDecompress;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.compression.BrotliDecoder;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.brotli.dec.BrotliInputStream;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CacheUtil {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(CacheUtil.class);

    String savePath = "./cache";

    public CacheUtil(String key) {
        savePath = String.format("%s/%s", savePath, key);
        FileUtils.createTxt(savePath);
    }

    public CacheUtil() {
    }


    public void overResp(FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} \n", url, httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE));

//        try {
//            // 解压数据
////            brotliDecoder.decompress(compressedData, decompressedData);
//            // 将ByteBuf转换为byte[]
//            byte[] byteArray = new byte[httpResponse.content().readableBytes()];
//            httpResponse.content().readBytes(byteArray);
////            BrotliUtils.decompress(byteArray);
//            log.info("byte:" + new String(byteArray, StandardCharsets.UTF_8));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //  打印原始响应信息
        String context = httpResponse.content().toString(Charset.defaultCharset());
//        log.info("defaultCharset:{}", context);
//        log.info("UTF_8:{}", httpResponse.content().toString(StandardCharsets.UTF_8));
//        log.info("ISO_8859_1:{}", httpResponse.content().toString(StandardCharsets.));
//        log.info("US_ASCII:{}", httpResponse.content().toString(StandardCharsets.US_ASCII));
        if (url != null) {
            if (url.equals("wanderprints.com/products.json?page=1&limit=20")) {
                ByteUtil.cover(httpResponse.content(), readResult("page.json"));
                return;
            }

            String result = readResult(getPath(url));
            if (result != null && !result.isEmpty()) {
//                ByteUtil.cover(httpResponse.content(), result);
            } else {
                saveResult(getPath(url), String.format("/* \n\tTime:%s\n\tUrl:%s \n*/\n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), url) + context);
//                saveResult("ISO_8859_1_" + getPath(url), String.format("/* \n\tTime:%s\n\tUrl:%s \n*/\n%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), url, httpResponse.content().toString(StandardCharsets.ISO_8859_1)));
//                saveResult("_US_ASCII" + getPath(url), String.format("/* \n\tTime:%s\n\tUrl:%s \n*/\n%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), url, httpResponse.content().toString(StandardCharsets.US_ASCII)));
            }
        }
    }

    public void overRespImg(FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
        String url = HttpUtil.buildUrl(pipeline.getHttpRequest());
        log.info("< < < < < < < < < < < < < < < < < < < < \n url: {} \n headers: {} \n", url, httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE));

        //  打印原始响应信息
        String context = httpResponse.content().toString(Charset.defaultCharset());

        if (url != null) {
            if (url.equals("wanderprints.com/products.json?page=1&limit=20")) {
                ByteUtil.cover(httpResponse.content(), readResult("page.json"));
                return;
            }
            String result = readResult(getPath(url));
            if (result != null && !result.isEmpty()) {
                ByteUtil.cover(httpResponse.content(), result);
            } else {
                saveResult(getPath(url), String.format("/* \n\tTime:%s\n\tUrl:%s \n*/\n%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), url, context));
            }
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

    public String getFilePath(String path) {
        return String.format("%s/%s", savePath, path);
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
//        CacheUtil cacheUtil = new CacheUtil();
//        System.out.println(cacheUtil.getPath("https://fe-static.xhscdn.com/formula-static/xhs-pc-web/public/js/runtime.fa8d6cd.js"));
        System.out.println(String.format("/* \n\tTime:%s\n\tUrl:%s \n*/\n%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "]","*****") );
    }

}
