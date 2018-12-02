package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDialingNumbers {
    public static AndroidDriver<MobileElement> driver;


    public static boolean isElementPresent(String id){
        try {
            driver.findElement(By.id(id));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        File app = new File("C:\\Users\\phild\\Downloads\\selendroid-test-app-0.17.0.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability("appPackage", "com.samsung.android.contacts");
        cap.setCapability("appActivity", "com.android.dialer.DialtactsActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        Thread.sleep(1000);

        try {
            driver.findElement(By.id("com.samsung.android.contacts:id/floating_action_button")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("android:id/button1")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("com.samsung.android.contacts:id/six")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/one")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/nine")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/four")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/five")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/six")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/five")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/six")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/three")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/seven")).click();
            driver.findElement(By.id("com.samsung.android.contacts:id/dialButtonImage")).click();
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
