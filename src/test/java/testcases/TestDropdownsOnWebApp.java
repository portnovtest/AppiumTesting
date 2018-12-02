package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDropdownsOnWebApp {

    //IOSElement, AndroidElement, MobileElement, WebElement
    public static AndroidDriver<WebElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        //starting the Appium server
        AppiumDriverLocalService service =  AppiumDriverLocalService.buildDefaultService();
//                .buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
//                .withAppiumJS(new File("C:/Users/phild/AppData/Roaming/npm/node_modules/appium/build/lib/appium.js"))
//                .withLogFile(new File("C:/Users/phild/Desktop/appiumlogs/logs.txt")));

        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://wikipedia.org");

        //driver.manage().deleteAllCookies();

        WebElement dropdown = driver.findElement(By.id("searchLanguage"));
        Select select = new Select(dropdown);
        select.selectByValue("hi");

        List<WebElement> values = dropdown.findElements(By.tagName("option"));
        System.out.println(values.size());
        for (WebElement value : values) {
            System.out.println(value.getAttribute("lang"));
        }

        System.out.println("---Print links-----");

        WebElement block = driver.findElement(By.cssSelector("div.other-projects"));
        List<WebElement> links = block.findElements(By.tagName("a"));
        System.out.println(links.size());
        for (WebElement link : links) {
            System.out.println(link.getAttribute("href") + "----"+link.getText());
        }
        Thread.sleep(2000);

        driver.quit();
        service.stop();
    }
}
