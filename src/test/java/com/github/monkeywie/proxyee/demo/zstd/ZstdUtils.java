package com.github.monkeywie.proxyee.demo.zstd;

import com.github.luben.zstd.Zstd;

import java.io.IOException;
import java.util.Arrays;

public class ZstdUtils {

    /**
     * 解压ZSTD压缩数据
     * @param compressedData 从接口获取的压缩字节数组
     * @param originalSizeHint 原始数据大小的估计值。如果未知，可以传0，但可能影响效率。
     * @return 解压后的原始字节数组
     * @throws IOException 如果解压失败
     */
    public static byte[] decompress(byte[] compressedData, long originalSizeHint) throws IOException {
        try {
            // Zstd.decompress需要目标字节数组
            // 1. 如果已知原始大小，直接使用
            if (originalSizeHint > 0) {
                byte[] destination = new byte[(int) originalSizeHint];
                long decompressedSize = Zstd.decompress(destination, compressedData);
                // 可根据decompressedSize调整返回数组，但通常与destination.length相等
                return destination;
            } else {
                // 2. 如果未知原始大小，先获取大小，再解压
                long decompressedSize = Zstd.decompressedSize(compressedData);
                if (decompressedSize > 0) {
                    byte[] destination = new byte[(int) decompressedSize];
                    Zstd.decompress(destination, compressedData);
                    return destination;
                } else {
                    // 3. 如果无法从帧头获取大小，使用流式API或传入一个足够大的数组（有风险）
                    // 这里提供一个简单但可能低效的备用方案：分配一个较大的缓冲区
                    byte[] destination = new byte[compressedData.length * 10]; // 假设压缩比最高为10:1
                    long size = Zstd.decompress(destination, compressedData);
                    return Arrays.copyOf(destination, (int) size);
                }
            }
        } catch (Exception e) {
            throw new IOException("Failed to decompress ZSTD data", e);
        }
    }
}
