package testNgLearning;

import org.testng.annotations.*;

public class LoginTest {

    @BeforeSuite
    public void setUp(){
        System.out.println("Initialize AppiumDriver");
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("driver.quit()");
    }

    @BeforeMethod
    public void launchApp(){
        System.out.println("Launching App");
    }

    @AfterMethod
    public void closeApp(){
        System.out.println("Closing the App");
    }

    @Test(priority = 1)
    public void doLogin(){
        System.out.println("Executing login test");
    }

    @Test(priority = 2)
    public void composeEmail(){

        System.out.println("Composing Email");
    }
}
