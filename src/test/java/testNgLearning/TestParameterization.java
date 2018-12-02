package testNgLearning;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.ExcelReader;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class TestParameterization {

    public static ExcelReader excel;
    public static AndroidDriver<MobileElement> driver;
    public static AppiumDriverLocalService service;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9+");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("appPackage", "io.selendroid.testapp");
        cap.setCapability("appActivity", ".HomeScreenActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.quit();
        service.stop();
        Thread.sleep(4000);
    }

    @Test(dataProvider = "getData")
    public void loginTest(String username, String password){
       driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys(username);
    }

    @DataProvider
    public static Object[][] getData(){

        if (excel==null){
            excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.xlsx");
        }

        String sheetName = "LoginTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        System.out.println("Total rows are : " + rows + " and total cols are : " + cols);
        System.out.println(excel.getCellData(sheetName, 0,2));

        Object[][] data = new Object[rows - 1][cols];


        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

            for (int colNum = 0; colNum < cols; colNum++) {
                // data[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;
    }

}
