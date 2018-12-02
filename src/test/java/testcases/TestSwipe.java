package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSwipe {
    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);


            driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
            driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
            driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("Shoes");
            driver.pressKeyCode(AndroidKeyCode.ENTER);
            List<MobileElement> names = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/item_title"));

            try {
                while (true) {

                    for (MobileElement name : names) {
                        if (name.getText().startsWith("ASICS")) {
                            name.click();
                            break;
                        }
                    }

                    WaitOptions options = new WaitOptions().withDuration(Duration.ofSeconds(5));
                    new TouchAction(driver).press(new PointOption().withCoordinates(500, 1900)).waitAction(options)
                            .moveTo(new PointOption().withCoordinates(500, 200)).release().perform();
                }
                } catch (NoSuchElementException e) {
            e.printStackTrace();
            service.stop();
            driver.quit();
        }
        Thread.sleep(4000);
        driver.quit();
        service.stop();
    }
}
