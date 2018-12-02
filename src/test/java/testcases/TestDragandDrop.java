package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class TestDragandDrop {
    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        //starting the Appium server
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//                .buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
//                .withAppiumJS(new File("C:/Users/phild/AppData/Roaming/npm/node_modules/appium/build/lib/appium.js"))
//                .withLogFile(new File("C:/Users/phild/Desktop/appiumlogs/logs.txt")));

        service.start();

        File app = new File(System.getProperty("user.dir") + "//apk//Drag.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());


        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

  //      driver.findElements(By.id("com.mobeta.android.demodslv:id/activity_title")).get(0).click();
        System.out.println(driver.getPageSource());

        //xml code
        //driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Basic')]")).click();

        String text = "Basic";
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")").click();
        //Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 5);

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mobeta.android.demodslv:id/text")));
        System.out.println(driver.getPageSource());

        MobileElement draggable =  driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Brad')]/preceding-sibling::android.widget.ImageView"));
        MobileElement droppable =  driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Kurt')]/preceding-sibling::android.widget.ImageView"));
        TouchAction action = new TouchAction(driver);

      //  action.longPress(draggable).moveTo(droppable).release().perform(); - does not work in Appium 1.9
        action.longPress(longPressOptions().withElement(element(draggable))).moveTo(element(droppable)).release().perform();

        Thread.sleep(5000);
        driver.quit();
        service.stop();
    }
}
