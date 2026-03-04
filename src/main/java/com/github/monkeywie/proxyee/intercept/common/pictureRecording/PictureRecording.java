package com.github.monkeywie.proxyee.intercept.common.pictureRecording;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;

public class PictureRecording {

    static String SAVE_PATH = "./cache/pictureRecording";


    public static String getFilePath(String path) {
        return String.format("%s/%s", SAVE_PATH, path);
    }

    public static String getHeaderFilePath(String path) {
        return getFilePath(String.format("/headers/%s.txt", path));
    }

    public static void addIntercept(HttpProxyInterceptPipeline pipeline) {
        pipeline.addLast(new Recording());
//        pipeline.addLast(new MockApi());
    }
}



