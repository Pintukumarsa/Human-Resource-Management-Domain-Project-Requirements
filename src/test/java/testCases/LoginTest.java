package testCases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;

import base.BaseClass;
import base.LoginPage;
import utilities.*;


@Listeners(utilities.ExtentListener.class)
public class LoginTest extends BaseClass {
	
	ExtentReports extent =ExtentManager.getReport();

    ExtentTest test;

    @DataProvider(name="LoginData")
    public Object[][] loginData()
    throws Exception {

        return ExcelUtils.getExcelData();
    }
    
    @Test(dataProvider="LoginData")
    public void loginTest(String username, String password)throws IOException {

        LoginPage lp =new LoginPage(driver);
        lp.login(username,password);
        if(username.equals("Admin")&& password.equals("admin123")) {
        	
        	WaitUtils.waitForVisibilityBy(driver,lp.searchInputField);
            Assert.assertTrue(lp.isDashboardDisplayed());
            
            extent =ExtentManager.getReport();
            test =extent.createTest(username);
            String path =ScreenshotUtil.captureScreenshot(driver,"Valid login");
            test.addScreenCaptureFromPath(path);
            
            lp.logoutUser();
            //driver.navigate().refresh();
            
        }
        else {
        	WaitUtils.waitForVisibilityBy(driver,lp.invalidMessage);
        	Assert.assertEquals(lp.invalidMessage(),"Invalid credentials");
            System.out.println("Invalid Username and Password");
            //driver.navigate().refresh();
            
        }
        
        
       
        
    }
}