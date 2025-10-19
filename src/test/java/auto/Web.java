//package auto;
//
//import com.kkmh.utils.HttpProxyUtil;
//import com.kkmh.utils.Selenium;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//
//public class Web {
//    private static JavascriptExecutor js;
//
//
//    public static void main(String[] args) throws InterruptedException {
//        // /Users/lv.mr/.cache/selenium/chromedriver/mac-arm64/130.0.6723.117/chromedriver
//        System.setProperty("webdriver.chrome.driver", "/Users/lv.mr/.cache/selenium/chromedriver/mac-arm64/130.0.6723.117/chromedriver");
//
//        WebDriver webDriver = Selenium.createProxyWebDriver();
//        webDriver.get("https://www.xiaohongshu.com/user/profile/5a177936e8ac2b535e9441b3?tab=fav&subTab=note");
//        js=(JavascriptExecutor) webDriver;
//        Object ob = js.executeScript("");
//
//        Thread.sleep(1000 * 100000);
//
//        webDriver.quit();
//    }
//}
