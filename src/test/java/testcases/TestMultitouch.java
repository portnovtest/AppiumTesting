package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;

public class TestMultitouch {
    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        //starting the Appium server
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//                .buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
//                .withAppiumJS(new File("C:/Users/phild/AppData/Roaming/npm/node_modules/appium/build/lib/appium.js"))
//                .withLogFile(new File("C:/Users/phild/Desktop/appiumlogs/logs.txt")));

        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("appPackage", "com.easylabs.multitouch");
        cap.setCapability("appActivity", ".MainActivity");


        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        WaitOptions options = new WaitOptions().withDuration(Duration.ofSeconds(2));
        TouchAction action1 = new TouchAction(driver).tap(new PointOption().withCoordinates(345, 1475)).waitAction(options);
        TouchAction action2 = new TouchAction(driver).tap(new PointOption().withCoordinates(742, 895)).waitAction(options);
        TouchAction action3 = new TouchAction(driver).tap(new PointOption().withCoordinates(925, 2105));

        new MultiTouchAction(driver).add(action1).add(action2).add(action3).perform();



        Thread.sleep(5000);
        driver.quit();
        service.stop();
    }
}
