package testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ToastVerification {

    public static AndroidDriver<MobileElement> driver;
    public static String destDir;
    public static DateFormat dateFormat;
    public static String scrPath;

    @Test
    public void toastValidationTest() throws IOException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", "Galaxy S9+");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "io.selendroid.testapp");
        capabilities.setCapability("appActivity", ".HomeScreenActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

        driver.findElementById("io.selendroid.testapp:id/buttonTest").click();
        driver.findElementById("android:id/button2").click();
        takeScreenShot();

        String Text=OCR(scrPath);

        System.out.println(Text);

        Assert.assertTrue(Text.contains("Activity will continue"), "Activity will continue");

    }

    //Method for Capturing Screenshot

    public static void takeScreenShot() {
        // Set folder name to store screenshots.
        destDir = "screenshots";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Set date format to set It as screenshot file name.
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name using current date time.
        String destFile = dateFormat.format(new Date()) + ".png";

        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
            scrPath = destDir+ "/" + destFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //OCR Reading

    public static String OCR(String ImagePath)
    {
        String result = null;
        File imageFile = new File(ImagePath);
        ITesseract instance = new Tesseract();
        try {
            result = instance.doOCR(imageFile);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

}
