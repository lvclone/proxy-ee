//package auto;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description: TODO(这里用一句话描述这个类的作用)
// * @Author Mr.Lv
// * @Date 2020/11/18 11:11
// */
//class Demo {
//
//    WebDriver driver;
//    String baseurl;
//    private JavascriptExecutor js;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // /Users/lv.mr/.cache/selenium/chromedriver/mac-arm64/130.0.6723.117/chromedriver
//        //谷歌浏览器的本地驱动
//        System.setProperty("webdriver.chrome.driver", "/Users/lv.mr/.cache/selenium/chromedriver/mac-arm64/130.0.6723.117/chromedriver");
//        //谷歌浏览器
//        driver=new ChromeDriver();
//        //设置访问网址
//        baseurl="https://www.baidu.com/";
//        //
//        //将WebElement类型的driver强制转换为js类型的
//        js=(JavascriptExecutor) driver;
//
//
//        //设置隐性等待
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        //窗口最大化
//        driver.manage().window().maximize();
//
//
//    }
//
//    @Test
//    void aa() throws Exception {
//
//        Object ob = js.executeScript("$(\"tbody > tr:nth-child(2) > td  > p\").innerHTML");
//
//        //打开网址
//        //使用Javascript语言打开百度网址
////        Object ob = js.executeScript("$(\"tbody > tr:nth-child(2) > td  > p\").innerHTML");
//
////        js.executeScript("window.location='https://www.baidu.com/';");
////        System.out.println("执行Javascript命令完成");
////        //等待3秒钟
////        Thread.sleep(3000);
////        //查找元素
////        //向下转型，将WebElement 转换为object
////        WebElement TestBOx=(WebElement)js.executeScript("return document.getElementById('kw');");
////        System.out.println("执行Javascript，根据ID查找元素完成");
////        TestBOx.sendKeys("test");
//
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        //等待
//        Thread.sleep(3000);
//        //关闭浏览器
//        driver.quit();
//    }
//
//
//}
