package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestHybridApp {
    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        File app = new File("C:\\Users\\phild\\IdeaProjects\\AppiumTesting\\apk\\HTML5test WebView_v1.0.2_apkpure.com.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability("appPackage", "com.html5test.webview");
        cap.setCapability("appActivity", "main.java.MainActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.findElement(By.id("com.html5test.webview:id/et")).clear();
        driver.findElement(By.id("com.html5test.webview:id/et")).sendKeys("http://google.com");
        driver.findElement(By.id("com.html5test.webview:id/go")).click();
        Set<String> contextNames = driver.getContextHandles();
        for (String context : contextNames) {
            System.out.println(context);
            if (context.contains("WEBVIEW")){
                driver.context(context);
            }
        }
        driver.findElement(By.name("q")).sendKeys("inside the web view !!!");

        Thread.sleep(4000);
        driver.quit();
        service.stop();

    }
}
