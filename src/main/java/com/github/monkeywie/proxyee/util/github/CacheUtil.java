package com.github.monkeywie.proxyee.util.github;


import com.github.monkeywie.proxyee.util.FileUtils;

import java.io.File;

public class CacheUtil {

    String savePath = "./cache";

    public CacheUtil(String key) {
        savePath = String.format("%s/%s", savePath, key);
        FileUtils.createTxt(savePath);
    }

    public CacheUtil() {
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
        try {
            FileUtils.write(getFilePath(path), result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

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

}
