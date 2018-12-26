package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestFindingElements {
    public static AndroidDriver driver;
    public static AppiumDriverLocalService service;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        service =  AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .withAppiumJS(new File("C:/Program Files (x86)/Appium/resources/app/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.dir")+ "/src/test/resources/logs/logs.txt")));

        service.start();


        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");


        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.findElement(MobileBy.AccessibilityId("visibleButtonTestCD")).click();
        //driver.findElement(MobileBy.xpath("//*[@content-desc='visibleButtonTestCD']")).click();
        //driver.findElement(MobileBy.xpath("//*[contains(@text,'Display text')]")).click();
        //driver.findElement(MobileBy.xpath("//android.widget.Button[contains(@text,'Display text')]")).click();
        //driver.findElement(MobileBy.xpath("//android.widget.LinearLayout/android.widget.Button[contains(@text,'Display text')]")).click();
        //driver.findElementsByAndroidUIAutomator("UiSelector().className(\"android.widget.CheckBox\").checked(true)").clear();
        //System.out.println(driver.findElementsByAndroidUIAutomator("UiSelector().className(\"android.widget.LinearLayout\").className(\"android.widget.Button\").index(1)").size());
        WebElement button = (WebElement) driver.findElementsByAndroidUIAutomator("UiSelector().className(\"android.widget.LinearLayout\").className(\"android.widget.Button\").index(1)").get(1);
        button.click();

        Thread.sleep(3000);
        driver.quit();


        service.stop();

    }
}
