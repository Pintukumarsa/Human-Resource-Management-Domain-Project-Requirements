package utilities;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;

public class ExtentListener implements ITestListener {

    ExtentReports extent =ExtentManager.getReport();

    ExtentTest test;
    
    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");

        try {

            WebDriver driver =
                    (WebDriver) result
                    .getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());

            String path =ScreenshotUtil.captureScreenshot(driver,result.getName());
            test.addScreenCaptureFromPath(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(
            ITestResult result) {
    	 System.out.println("FAILED TEST : "+ result.getName());

    	    System.out.println("REASON : "+ result.getThrowable());

    	   test.fail(result.getThrowable());


        try {

            WebDriver driver =
                    (WebDriver) result
                    .getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());

            String path =ScreenshotUtil.captureScreenshot(driver,result.getName());

            test.addScreenCaptureFromPath(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}