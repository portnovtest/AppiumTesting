package testNgLearning;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestDependencies {
    @Test(priority = 1, groups = "high")
    public void doLogin(){
        System.out.println("Executing login test");
        Assert.fail("Login not successful");
    }

    @Test(priority = 2, dependsOnMethods = "doLogin", groups = "med")
    public void composeEmail(){

        System.out.println("Composing Email");
    }

    @Test(groups = "high")
    public void isSkip(){
        throw new SkipException("Skipping the test case");
    }
}
