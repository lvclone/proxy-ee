package com.github.monkeywie.proxyee.util.github.http2.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.brotli.dec.BrotliInputStream;

import java.io.IOException;

import okhttp3.internal.http.HttpHeaders;

/**
 * 响应解压缩拦截器
 *
 * @author zhang.rx
 * @since 2024/4/11
 */
@Slf4j
public class ResponseCompressionInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        log.info("Content-Encoding:{}", originalResponse.header("Content-Encoding"));
        // 响应头的Content-Encoding中包含gzip，说明响应需要解压
        if ("gzip".equalsIgnoreCase(originalResponse.header("Content-Encoding"))) {
            return originalResponse.newBuilder()
                    .body(gzip(originalResponse))
                    .build();
        } else if ("br".equalsIgnoreCase(originalResponse.header("Content-Encoding"))) {
            return originalResponse.newBuilder()
                    .body(br(originalResponse))
                    .build();
        }
        return originalResponse;
//        return uncompressed(originalResponse);
    }

    private ResponseBody gzip(Response response) throws IOException {
        BufferedSource source = Okio.buffer(new GzipSource(response.body().source()));
        return ResponseBody.create(response.body().contentType(), response.body().contentLength(), source);
    }

    private ResponseBody br(Response response) throws IOException {
        BufferedSource source = Okio.buffer(Okio.source(new BrotliInputStream(response.body().source().inputStream())));
        return ResponseBody.create(response.body().contentType(), response.body().contentLength(), source);
    }

    //处理压缩格式
    public Response uncompressed(Response response) throws IOException {
        if (!HttpHeaders.promisesBody(response)) {
            return response;
        }
        ResponseBody body = response.body();
        if (body == null) {
            return response;
        }
        String encoding = response.header("Content-Encoding");
        if (encoding == null) {
            return response;
        }
        BufferedSource decompressedSource;
        switch (encoding) {
            case "br": //Brotli压缩格式解压
                decompressedSource = Okio.buffer(Okio.source(new BrotliInputStream(body.source().inputStream())));
                break;
            case "gzip": //gzip压缩格式解压
                decompressedSource = Okio.buffer(new GzipSource(body.source()));
                break;
            default: {
                //默认不处理
                return response;
            }
        }
        //返回解压后的数据
        return response.newBuilder()
//                .body(ResponseBody.create(response.body().contentType(), response.body().contentLength(), decompressedSource))
                .body(ResponseBody.create(decompressedSource, body.contentType(), -1))
                .build();

    }
}

