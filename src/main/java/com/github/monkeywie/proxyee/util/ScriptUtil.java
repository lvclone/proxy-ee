//package com.github.monkeywie.proxyee.util;
//
//import jdk.nashorn.api.scripting.NashornScriptEngine;
//import jdk.nashorn.api.scripting.ScriptObjectMirror;
//
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.concurrent.*;
//
///**
// * @program: demo
// * @description:
// * @author: Zhuozhuang.Lv
// * @create: 2019-08-22 21:22
// */
//
//public class ScriptUtil {
//
//    ScriptEngineManager sem;
//    NashornScriptEngine engine;
//
//    public ScriptUtil(String fileName) {
//        init(fileName);
//    }
//
//    void init(String fileName) {
//        sem = new ScriptEngineManager();
//        engine = (NashornScriptEngine) sem.getEngineByName("javascript");
//        try {
//            engine.eval(new FileReader(this.getClass().getResource("/" + fileName + ".js").getPath()));
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    ExecutorService executor;
//
//    public Collection runJS(String name, Object... args) {
//        try {
//            Callable<Collection> addition = new Callable<Collection>() {
//                @Override
//                public Collection call() {
//                    try {
//                        ScriptObjectMirror mirror = (ScriptObjectMirror) engine.invokeFunction(name, args);
//                        return mirror.values();
//                    } catch (ScriptException | NoSuchMethodException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            };
//
//            executor = Executors.newCachedThreadPool();
//            Collection collection = executor.submit(addition).get();
//            close();
//            return collection;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<String>();
//    }
//
//    void close() {
//        try {
//            executor.awaitTermination(1, TimeUnit.SECONDS);
//            executor.shutdownNow();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        String data= "https://h5.waimai.meituan.com/login?force=true&back_url=https%3A%2F%2Fh5.waimai.meituan.com%2Fwaimai%2Fmindex%2Fmenu%3FmtShopId%3D935006996102775%26utm_source%3Dappshare";
//        data = "https://verify.meituan.com/v2/ext_api/login/info";
//        data = "https://verify.meituan.com/v2/ext_api/login/verify";
//
////        System.out.println(new ScriptUtil("xhs").runJS("run",data).toArray()[0].toString());
//        System.out.println(new ScriptUtil("xhs").runJS("re").toArray()[0].toString());
//    }
//}
