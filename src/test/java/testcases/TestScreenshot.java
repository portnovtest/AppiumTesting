package testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestScreenshot {
    public static AndroidDriver<MobileElement> driver;
    public static String destDir;
    public static DateFormat dateFormat;

    public static void takeScreenshot() throws IOException {

        //directory
        destDir = "screenshots";
        //capturing screenshot
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Set date
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
        //create folder
        new File(destDir).mkdir();

        String destFile = dateFormat.format(new Date()) + ".png";

        FileUtils.copyFile(srcFile, new File(destDir + "/" + destFile));
    }

    public static String elementScreenshot(WebElement ele){
        File screenshotLocation = null;

        File srcFile = null;
        try {
            srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImage = ImageIO.read(srcFile);
            // Get the location of element on the page
            Point point = ele.getLocation();
            // Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();
            //Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = fullImage.getSubimage(point.getX(),point.getY(),eleWidth,eleHeight);
            ImageIO.write(eleScreenshot, "png", srcFile);

            String fileName = "newscreenshot";

            screenshotLocation = new File("./screenshots/" + fileName + ".jpg");
            FileUtils.copyFile(srcFile, screenshotLocation);

            System.out.println(screenshotLocation.toString());

        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }

        return screenshotLocation.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("appPackage", "io.selendroid.testapp");
        cap.setCapability("appActivity", ".HomeScreenActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        takeScreenshot();

        elementScreenshot(driver.findElement(By.id("io.selendroid.testapp:id/waitingButtonTest")));
        Thread.sleep(4000);
        driver.quit();
        service.stop();
    }
}
