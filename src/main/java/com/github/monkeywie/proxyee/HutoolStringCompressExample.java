//package com.github.monkeywie.proxyee;
//
//import cn.hutool.core.util.ZipUtil;
//
//import java.util.Base64;
//
//public class HutoolStringCompressExample {
//
//
//
//
//        public static void main(String[] args) {
//            // 原始字符串
//            String testStr= "测试hutool压缩";
//            testStr = "https://blog.csdn.net/qq_39763510/article/details/139124463";
//
//            // 压缩字符串
//            byte[] compressed = ZipUtil.zlib(testStr,"utf-8",1);
//            // 将压缩后的字节数组转换为Base64编码字符串
//            String compressedString = Base64.getEncoder().encodeToString(compressed);
//            System.out.println("压缩后的字符串: " + compressedString);
//
//            // 解压缩字符串
//            byte[] decompressed = ZipUtil.unZlib(Base64.getDecoder().decode(compressedString));
//            // 解压缩后转换为字符串
//            String decompressedString = new String(decompressed);
//            System.out.println("解压缩后的字符串: " + decompressedString);
//        }
//}
