package listeners;

import org.testng.*;

public class CustomListeners implements ITestListener, ISuiteListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        System.out.println("Screenshot captured");
        System.setProperty("org.uncommons.reportng.escape-output","false");

        Reporter.log("<a href=\"abcd.jpg\">Screenshot captured</a>");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

        System.out.println("Starting the test suite");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        System.out.println("Ending the test suite");
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("Starting");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println("Finishing");

    }
}
