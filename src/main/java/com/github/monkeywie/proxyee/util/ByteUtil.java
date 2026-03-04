package com.github.monkeywie.proxyee.util;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteUtil {
    private final static InternalLogger log = InternalLoggerFactory.getInstance(ByteUtil.class);

    public static int findText(ByteBuf byteBuf, String str) {
        return findText(byteBuf, str, true);
    }

    public static int findText(ByteBuf byteBuf, String str, boolean appLen) {
        byte[] text = str.getBytes();
        log.info("str.length: {}", text.length);
        int matchIndex = 0;
        for (int i = byteBuf.readerIndex(); i < byteBuf.readableBytes(); i++) {
            for (int j = matchIndex; j < text.length; j++) {
                if (byteBuf.getByte(i) == text[j]) {
                    matchIndex = j + 1;
                    if (matchIndex == text.length) {
                        log.info("matchIndex: {} i: {}", matchIndex, i);
                        return appLen ? i : i - (text.length);
                    }
                } else {
                    matchIndex = 0;
                }
                break;
            }
        }
        return -1;
    }

    public static ByteBuf insertText(ByteBuf byteBuf, int index, String str) {
        return insertText(byteBuf, index, str, Charset.defaultCharset());
    }

    public static ByteBuf insertText(ByteBuf byteBuf, int index, String str, Charset charset) {

        byte[] begin = new byte[index + 1];
        byte[] end = new byte[byteBuf.readableBytes() - begin.length];
        log.info("index:{} str:{} {} ", index + 1, byteBuf.readableBytes(), begin.length);

        byteBuf.readBytes(begin);
        byteBuf.readBytes(end);
        byteBuf.writeBytes(begin);
        byteBuf.writeBytes(str.getBytes(charset));
        byteBuf.writeBytes(end);
        return byteBuf;
    }


    public static void buildContext(Channel clientChannel, JSONObject connection, byte[] bytes) {
        HttpResponse httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        connection.forEach((k, v) -> {
//            content-type
            httpResponse.headers().set(k.toLowerCase(), v);
        });
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, bytes.length);

        clientChannel.writeAndFlush(httpResponse);
        clientChannel.writeAndFlush(new DefaultLastHttpContent().content().writeBytes(bytes));

    }
    public static void buildContext(Channel clientChannel, String contentType, Object connection,  byte[] bytes) {
        HttpResponse httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, bytes.length);
        httpResponse.headers().set(HttpHeaderNames.CONNECTION, connection);
        clientChannel.writeAndFlush(httpResponse);
        clientChannel.writeAndFlush(new DefaultLastHttpContent().content().writeBytes(bytes));

    }

    /**
     * 覆盖
     * @param byteBuf
     * @param str
     */
    public static void cover(ByteBuf byteBuf, String str){
        cover(byteBuf, str, Charset.defaultCharset());
    }

    /**
     * 覆盖
     * @param byteBuf
     * @param str
     * @param charset
     */
    public static void cover(ByteBuf byteBuf, String str, Charset charset){
        byteBuf.skipBytes(byteBuf.writerIndex());
        byteBuf.discardReadBytes();
        byteBuf.writeBytes(str.getBytes(charset));
    }

    /**
     * String -> ByteBuf
     * @param str
     * @return
     */
    public static ByteBuf valueOf(String str) {
//        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello, Netty!".getBytes());

        ByteBuf httpResponseJsBuf = Unpooled.buffer();
        httpResponseJsBuf.writeBytes(str.getBytes());
//        System.out.println(httpResponseJsBuf.writerIndex());
        return httpResponseJsBuf;
    }


    public static void main(String[] args) {
        String str = FileUtils.readLine("./cache/splidejs/luanma_webpack-runtime-654f5c8e2fdb99bf659e.js");
//        String str = FileUtils.readLine("./cache/splidejs/webpack-runtime-654f5c8e2fdb99bf659e.js");
        ByteBuf byteBuf = Unpooled.copiedBuffer(str.getBytes());
        String context = byteBuf.toString(StandardCharsets.UTF_8);

        System.out.println(context);
//        str.getBytes(charset);
    }

}
