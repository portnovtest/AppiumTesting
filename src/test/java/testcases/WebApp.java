package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebApp {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");

        //cap.setCapability("device", "Android");
        //cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        //cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        driver.get("http://google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("q")).sendKeys("Hello Appium");

        Thread.sleep(3000);

        driver.quit();
    }
}
