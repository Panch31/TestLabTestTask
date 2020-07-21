//package util;
//
//import io.qameta.allure.Attachment;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import TestBase;
//
//public class TestListener implements ITestListener {
//
//
//        @Override
//        public void onTestStart(ITestResult result) {
//        }
//
//        @Override
//        public void onTestSuccess(ITestResult result) {
//        }
//
//        @Override
//        public void onTestFailure(ITestResult result) {
//        makeScreenshot();
//        System.out.println("Started");
//        if (result.getThrowable()!=null) {
//            result.getThrowable().printStackTrace();
//        }
//    }
//
//    @Attachment(value = "Page screenshot", type = "image/png")
//    private byte[] makeScreenshot() {
//        return ((TakesScreenshot) TestBase.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//
//        @Override
//        public void onTestSkipped(ITestResult result) {
//            System.out.println("Skip of test cases and its details are : "+result.getName());
//        }
//
//        @Override
//        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//            System.out.println("Failure of test cases and its details are : "+result.getName());
//        }
//
//        @Override
//        public void onStart(ITestContext context) {
//        }
//
//        @Override
//        public void onFinish(ITestContext context) {
//        }
//    }
