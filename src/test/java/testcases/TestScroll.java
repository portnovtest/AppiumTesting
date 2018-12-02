package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestScroll {
    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("appPackage", "com.samsung.android.contacts");
        cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);


        System.out.println(driver.getPageSource());

        String text = null;

        try {
            driver.findElementById("com.samsung.android.contacts:id/ftu_later_text").click();
            Thread.sleep(1000);
            text = "Gus";

           MobileElement ele =  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/list\")).scrollIntoView(new UiSelector().text(\"" + text + "\"))");

           //Left to right swipe
//            WaitOptions options = new WaitOptions().withDuration(Duration.ofSeconds(5));
//            new TouchAction(driver).press(new PointOption().withCoordinates(ele.getLocation().x + 200, ele.getLocation().y)).waitAction(options)
//                    .moveTo(new PointOption().withCoordinates(ele.getLocation().x + 1100, ele.getLocation().y)).release().perform();

            //Right to left swipe
            WaitOptions options = new WaitOptions().withDuration(Duration.ofSeconds(5));
            new TouchAction(driver).press(new PointOption().withCoordinates(ele.getLocation().x + 900, ele.getLocation().y)).waitAction(options)
                    .moveTo(new PointOption().withCoordinates(ele.getLocation().x + 100, ele.getLocation().y)).release().perform();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            service.stop();
            driver.quit();
        }

        Thread.sleep(5000);
        driver.quit();
        service.stop();
    }



}
