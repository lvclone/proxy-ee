//package auto;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import net.lightbody.bmp.BrowserMobProxy;
//import net.lightbody.bmp.BrowserMobProxyServer;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class ChromeTest {
//
//    private WebDriver driver;
//
//    BrowserMobProxy proxy;
//
//    @BeforeClass
//    public static void setupClass() {
//        WebDriverManager.chromedriver().setup();
//    }
//
//    @Before
//    public void setupTest() {
//
//        proxy = new BrowserMobProxyServer();
//        proxy.start(0);
//        int port = proxy.getPort(); // get the JVM-assigned port
//        // Selenium or HTTP client configuration goes here
////        System.out.println(port);
//        ChromeOptions options = new ChromeOptions();
//        // 设置代理ip
//        String ip = "localhost:" + port;
//        options.addArguments("--proxy-server=http://" + ip);
//        driver = new ChromeDriver(options);
//    }
//
//    @After
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test
//    public void test() {
//
//        driver.get("http://www.baidu.com/");
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
////        Har har = proxy.getHar();
////        if(ObjectUtils.notIsEmpty(har)){
////            HarLog harLog = har.getLog();
////            List<HarEntry> harEntries =  harLog.getEntries();
////
////            for (HarEntry harEntry : harEntries) {
////                if(ObjectUtils.notIsEmpty(harEntry)){
////                    System.out.println("api:\t"+JSON.toJSONString(harEntries));
////                }else{
////                    System.out.println("AAA");
////                }
////            }
////        }
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        proxy.stop();
//    }
//
//}
