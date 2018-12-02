package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TestNativeElements {
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

        AppiumDriverLocalService service =  AppiumDriverLocalService.buildDefaultService();
        service.start();
        File app = new File("C:\\Users\\phild\\Downloads\\selendroid-test-app-0.17.0.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability("appPackage", "io.selendroid.testapp");
        cap.setCapability("appActivity", ".HomeScreenActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        if (driver.isDeviceLocked()){
            driver.unlockDevice();
        }
        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Hello appium");
        System.out.println(driver.findElements(By.className("android.widget.Button")).size());
        List<MobileElement> btn =  driver.findElements(By.className("android.widget.Button"));
        for (MobileElement button : btn) {
            if (button.getAttribute("text").contains("Display text view")){
                button.click();
            }
        }

        driver.openNotifications();
        if (isElementPresent("com.android.systemui:id/clear_all")){
            driver.findElement(By.id("com.android.systemui:id/clear_all")).click();
        }
        driver.pressKeyCode(4);
        driver.closeApp();
        driver.removeApp("io.selendroid.testapp");
        Thread.sleep(10000);
        System.out.println("Status of App installation: " + driver.isAppInstalled("io.selendroid.testapp"));



        if (!driver.isAppInstalled("io.selendroid.testapp")){
            driver.installApp("C:\\Users\\phild\\Downloads\\selendroid-test-app-0.17.0.apk");
            Activity activity = new Activity("io.selendroid.testapp", ".HomeScreenActivity");
            driver.startActivity(activity); //switching the apps - selendroid test app, message app
        }

        Thread.sleep(5000);
        driver.quit();
        service.stop();

    }
}
