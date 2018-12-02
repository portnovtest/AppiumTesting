package testNgLearning;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NativeTest {

    AndroidDriver<MobileElement> driver;
    public static AppiumDriverLocalService service;

    public String setMobileNumber=null;
    public static String firstDeviceName = "emulator-5554";
    public static String secondDeviceName = "44354f5747553098";

    @BeforeClass
    @Parameters({"port","deviceID"})
    public void startTime(@Optional String port1,@Optional String device_id) throws IOException {

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (device_id.equalsIgnoreCase(firstDeviceName)){
            System.out.println(port1 + "-" + System.currentTimeMillis());
            System.out.println(device_id + "-" + System.currentTimeMillis());
            capabilities.setCapability("browserName", "");
            capabilities.setCapability("deviceName", firstDeviceName);
            capabilities.setCapability("udid", firstDeviceName);
            capabilities.setCapability("platformVersion", "7.1.1");
            capabilities.setCapability("appPackage", "io.selendroid.testapp");
            capabilities.setCapability("appActivity", ".HomeScreenActivity");

            setMobileNumber=device_id;
        } else if (device_id.equalsIgnoreCase(secondDeviceName)){
            System.out.println(port1 + "-" + System.currentTimeMillis());
            System.out.println(device_id + "-" + System.currentTimeMillis());
            capabilities.setCapability("browserName", "");
            capabilities.setCapability("deviceName", secondDeviceName);
            capabilities.setCapability("udid", secondDeviceName);
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("appPackage", "io.selendroid.testapp");
            capabilities.setCapability("appActivity", ".HomeScreenActivity");
        }

        driver = new AndroidDriver<>(new URL("http://localhost:4444/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testcase() throws InterruptedException {
        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Hello Appium!!!");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        driver.quit();
        service.stop();
        Thread.sleep(4000);

    }
}
