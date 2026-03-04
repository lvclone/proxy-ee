//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class Selenium {
//
//    static String ip = "127.0.0.1";
//    static int port = 9999;
//
//    private static ChromeOptions getLambdaChromeOptions(String ip, int port) {
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--disable-gpu");
////        options.addArguments("--headless");
////        options.addArguments("--window-size=1366,768");
////        options.addArguments("--single-process");
////        options.addArguments("--no-sandbox");
//        options.addArguments("--user-data-dir=./seleniumCache/userDataDir");
//        options.addArguments("--data-path=./seleniumCache/dataPath");
//        options.addArguments("--homedir=./seleniumCache/homedir");
//        options.addArguments("--disk-cache-dir=./seleniumCache/diskCacheDir");
//        if (null != ip && !ip.isEmpty() && port > 0) {
//            String addr = String.format("--proxy-server=http://%s:%d", ip, port);
//            System.out.println(addr);
//            options.addArguments(addr);
//        }
//        return options;
//    }
//
//    private static ChromeOptions getLambdaChromeOptions() {
//        return getLambdaChromeOptions(null, 0);
//    }
//
//    public static WebDriver createWebDriver() {
//        return new ChromeDriver(getLambdaChromeOptions());
//    }
//    boolean proxy = false;
//    public static WebDriver createProxyWebDriver() {
//        return new ChromeDriver(getLambdaChromeOptions(ip, port));
//    }
//
//}
