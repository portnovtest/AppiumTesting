package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestHardwareKeys {
    public static AndroidDriver driver;
    public static AppiumDriverLocalService service;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        service =  AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .withAppiumJS(new File("C:/Program Files (x86)/Appium/resources/app/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.dir")+ "/src/test/resources/logs/Application.log")));
        service.start();

        File app = new File("C:/Users/phild/Downloads/selendroid-test-app-0.17.0.apk");
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        //cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        //cap.setCapability("appPackage", "com.samsung.android.contacts");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        // cap.setCapability("appActivity", "com.samsung.dialer.dialpad.VVM");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();

//        Actions action = new Actions(driver);
//        action.sendKeys("Appium").perform();

//        try {
//            driver.toggleAirplaneMode();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Thread.sleep(3000);
        driver.quit();

        service.stop();

    }
}
