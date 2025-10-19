package com.github.monkeywie.proxyee.util;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class FileUtils {
    //    protected static final Log logger = LogFactory.getLog(FileUtils.class);
    static boolean exceptionThrow = false;


    public static void write2(ByteBuf byteBuf, String filePath) {
        createTxt(filePath);

        // 使用 FileOutputStream 和 FileChannel 将 ByteBuf 写入文件
        try (FileOutputStream fos = new FileOutputStream(filePath);
             FileChannel fileChannel = fos.getChannel()) {

            // 将 ByteBuf 的内容写入文件
            while (byteBuf.readableBytes() > 0) {
                fileChannel.write(byteBuf.nioBuffer());
            }

            System.out.println("数据已成功写入文件: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            // 释放 ByteBuf 资源
//            byteBuf.release();
//        }

    }

    public static void write(ByteBuf byteBuf, String filePath) {
//        createTxt(filePath);

        // 创建文件输出流
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // 读取ByteBuf中的数据到字节数组
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);

            // 将字节数组写入文件
            fos.write(bytes);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            // 释放ByteBuf资源
//            byteBuf.release();
//        }
    }

    public static void write(String filePath, String context) {
        FileUtils.mkdirs(filePath);
//        createTxt(filePath);
        try {
            org.apache.commons.io.FileUtils.write(getFile(filePath), context, "UTF-8", false);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void saveImage(ByteBuf content, String url) {
        FileUtils.mkdirs(url);

        byte[] bytes = new byte[content.readableBytes()];
        log.info("bytes:{}", bytes.length);
        if (bytes.length > 0) {
            content.readBytes(bytes);
            Path path = Paths.get(url);
            try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
                fos.write(bytes);
                System.out.println("图片保存成功：" + url);
            } catch (IOException e) {
                System.err.println("图片保存失败：" + e.getMessage());
            }
            content.writeBytes(bytes);
        }
    }

    public static void writeln(String filePath, String context, boolean notExistCreateFile, boolean append) {
        createTxt(filePath);
        write(filePath, context + "\n", notExistCreateFile, append);
    }

    public static void write(String filePath, String context, boolean notExistCreateFile, boolean append) {
        try {
            org.apache.commons.io.FileUtils.write(getFile(filePath), context, "UTF-8", append);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static JSONObject readJSONO(String filePath) {
        return JSONUtils.toJSONObject(readLine(filePath));
    }

    /**
     * 读取文本
     *
     * @param filePath 文件路径
     * @return
     */
    public static String readLine(String filePath) {
        List<String> strings = readLines(filePath);
        StringBuffer sb = new StringBuffer();
        for (String string : strings) {
            sb.append(string);
        }
        if (ObjectUtils.notIsEmpty(sb.toString())) {
            return sb.toString();
        }
        return "";
    }

    public static List<String> readLines(String filePath) {
        List<String> strings = null;
        try {
            strings = org.apache.commons.io.FileUtils.readLines(getFile(filePath), "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
//        logger.info("strings:\t" + JSON.toJSONString(strings));
//        logger.info("strings:\t" + strings.size());
        return strings;
    }

    static boolean isFile(String filePath) {
        return indexOfNum(filePath, ".") >= 1;
    }

    public static Integer indexOfNum(String originStr, String targetStr) {
        int res = 0;

        int i = originStr.indexOf(targetStr);

        while (i != -1) {
            i = originStr.indexOf(targetStr, i + 1);
            res++;
        }
        return res;

    }

    /**
     * 获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFile(String filePath) {
        return getFile(filePath, true);
    }

    /**
     * 获取文件
     *
     * @param filePath           文件 路径
     * @param notExistCreateFile 不存在创建文件
     * @return 文件
     */
    public static File getFile(String filePath, boolean notExistCreateFile) {
        File file = new File(filePath);
//        System.out.println(file.getPath());
//        System.out.println(file.getParentFile().getParentFile().getParentFile());
        if (file.exists()) {
            return file;
        } else {
            exceptionPromptThrown("文件不存在");
        }

        // 新建文件
        if (notExistCreateFile) {
            if (isFile(filePath)) {
                try {
                    if (!file.createNewFile()) {
                        throw new IllegalArgumentException("创建文件失败！");
                    }
                } catch (IOException e) {
                    throw new IllegalArgumentException("IO异常~ " + e.getMessage());
                }
            } else {
                if (!file.mkdir()) {
                    throw new IllegalArgumentException("创建文件失败！");
                }
            }
        }
        return file;
    }

    public static byte[] convertImageToByteArray(String url, String formatName) throws IOException {
        log.info("convertImageToByteArray.url:{}", url);
        File imageFile = new File(url);
        BufferedImage image = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, formatName, baos); // 使用png格式作为例子，你可以根据需要更改格式
        byte[] imageBytes = baos.toByteArray();
        baos.close();
        return imageBytes;
    }

    public static void exceptionPromptThrown(String msg) {
        if (exceptionThrow) {
            throw new IllegalArgumentException(msg);
        } else {
//            logger.info(msg);
        }
    }


    public static void createTxt(String path) {
        if (new File(path).exists()) {
            log.info("exists");
            return;
        }
        path = path.replaceAll("\\\\", "/");
        String[] files = path.split("/");
        String createFilder = "";
        for (int i = 0; i < files.length; i++) {
            if (i == (files.length - 1)) {
                new File(createFilder).mkdirs();
                try {
                    System.out.println("\t" + createFilder + files[i]);
                    new File(createFilder += files[i]).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                createFilder += files[i] + "/";
            }
            System.out.println(createFilder);
        }
    }


    public static void mkdirs(String path) {
        log.info("mkdirs:{}", path);
        path = path.replaceAll("\\\\", "/");
        boolean isFile = false;
        String[] files = path.split("/");
        if (path.lastIndexOf('/') != path.length() - 1) {
            isFile = true;
        }

        if (isFile) {
            StringBuilder createFilder = new StringBuilder();
            for (int i = 0; i < files.length - 1; i++) {
                createFilder.append(files[i]).append("/");
            }

            log.info("createFilder:{}", createFilder.toString());
            if (!new File(createFilder.toString()).exists()) {
                new File(createFilder.toString()).mkdirs();
            }
//
//            try {
//                if (lastFileCre) {
//                    new File(path).createNewFile();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            new File(path).mkdirs();
        }
    }


    public static void main(String[] args) throws IOException {
//        createTxt("./cache/aaaa/");

        mkdirs("./aaa/a/b/c/");

//        new File("./cache/a/b/c/d").mkdirs();


    }

}
