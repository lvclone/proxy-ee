package com.github.monkeywie.proxyee.demo.zstd;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainProcessor {


    public static void main(String[] args) {
        process();
    }

    public static void process() {
        try {
            // 1. 发送请求，获取压缩数据
            byte[] compressedData = fetchDataWithHttpClient();
            System.out.println("Received compressed data size: " + compressedData.length + " bytes");

            // 2. 解压数据
            // 这里假设不知道原始大小，让工具类自己去探测
            byte[] originalData = ZstdUtils.decompress(compressedData, 0);
            System.out.println("Decompressed data size: " + originalData.length + " bytes");

            // 3. 处理原始数据
            processDecompressedData(originalData);

        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public static byte[] fetchDataWithHttpClient() throws Exception {
        // 1. 创建HttpClient实例 (会自动处理HTTPS基础配置)
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // 2. 创建HttpGet请求对象
            HttpGet httpGet = new HttpGet("https://prewxacode.wxqcloud.qq.com.cn/weapp/release_encrypt/97_zu_t-vTLfuN-soFTTiATjx-Ihe9GW1zjef4qI879ZePK7jest7PZrsR4oaFoF4BUW4kgGyVo_Wygh1s4.zstd?busi_id=1005&app=wxd704b6fcc8ba8e9e");

            // 3. (可选) 设置请求头
            httpGet.setHeader("User-Agent", "Mozilla/5.0 ( compatible ) ");
            // httpGet.setHeader("Authorization", "Bearer your_access_token");

            // 4. 执行请求
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

                // 5. 检查状态码
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    // 6. 获取响应实体
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // 7. 使用EntityUtils将实体内容直接转换为字节数组
                        // EntityUtils内部会负责流的管理和关闭
                        return EntityUtils.toByteArray(entity);
                    }
                } else {
                    throw new IOException("HTTP request failed with code: " + statusCode);
                }
            }
        }
        return new byte[0];
    }

    private static void processDecompressedData(byte[] originalData) {
        // 这是最灵活的部分，取决于接口返回的原始数据的格式。
        // 根据您的业务上下文，它可能是：
        // - 纯文本（如JSON、XML）：用 new String(originalData, StandardCharsets.UTF_8) 转换为字符串。
        // - 图像文件（PNG、JPEG）：用 ImageIO.read(new ByteArrayInputStream(originalData)) 读取。
        // - 另一种二进制协议：需要按照对应的协议规范进行解析。
        // - 加密后的数据：可能需要进一步解密。资料[[67]][[69]] 提到了微信可能使用的加密方式。

        // 示例：如果预期是JSON
         String jsonString = new String(originalData, StandardCharsets.UTF_8);
        System.out.println(jsonString);
//         YourDataClass data = new Gson().fromJson(jsonString, YourDataClass.class);

        System.out.println("Data decompressed successfully. Format needs to be determined.");
    }
}
