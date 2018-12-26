package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class TestDeployApp {
    public static AndroidDriver<WebElement> driver;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
     //  AppiumDriverLocalService service =  AppiumDriverLocalService.buildDefaultService();
        AppiumDriverLocalService service =  AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .withAppiumJS(new File("C:/Program Files (x86)/Appium/resources/app/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.dir")+ "/src/test/resources/logs/logs.txt")));

        service.start();

        File app = new File("C:/Users/phild/Downloads/selendroid-test-app-0.17.0.apk");
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability("appPackage", "io.selendroid.testapp");
        //cap.setCapability("appPackage", "com.samsung.android.contacts");
        cap.setCapability("appActivity", ".HomeScreenActivity");
       // cap.setCapability("appActivity", "com.samsung.dialer.dialpad.VVM");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        log.debug("Application launched");

        Thread.sleep(3000);
        driver.quit();

        log.debug("Application closed");
        service.stop();

    }
}
