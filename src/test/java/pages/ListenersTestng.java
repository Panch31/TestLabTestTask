package pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.TestBase;

public class ListenersTestng implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
// TODO Auto-generated method stub
    }

    @Override
    public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Success of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot(result);
        System.out.println("Started");
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((TestBase) currentClass).getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skip of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
// TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
    }
}
//    @Override
//    public void onTestFailure(ITestResult result) {
//        makeScreenshot();
//        System.out.println("Started");
//        if (result.getThrowable()!=null) {
//            result.getThrowable().printStackTrace();
//        }
//    }
//
//    @Attachment(value = "Page screenshot", type = "image/png")
//    private byte[] makeScreenshot() {
//        return ((TakesScreenshot) AppManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }
