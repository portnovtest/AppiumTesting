package testNgLearning;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateButtonTextTest {

    @Test
    public void validateButton(){
        String expectedText = "Submit";
        String actualText = "OK";
        Assert.assertEquals(actualText,expectedText);
    }
}
