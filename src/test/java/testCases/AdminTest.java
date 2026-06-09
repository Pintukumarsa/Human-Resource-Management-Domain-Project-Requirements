package testCases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.AdminPage;
import base.BaseClass;
import base.LoginPage;

@Listeners(utilities.ExtentListener.class)
public class AdminTest extends BaseClass{
	AdminPage ap;
    
    @BeforeClass
    public void loginSetup() throws IOException {
        LoginPage lp =new LoginPage(driver);
        lp.login("Admin","admin123");
        Assert.assertTrue(lp.isDashboardDisplayed());
        System.out.println("Login Test Passed");
        ap =new AdminPage(driver);
    }


    // TC-01
    @Test(priority = 1)
    public void verifyLeftMenuCount(){

        int count =ap.getLeftMenuCount();
        Assert.assertEquals(count,12);

    }

    // TC-02
    @Test(priority = 2)
    public void searchByUsernameTest() {

        ap.clickAdmin();

        int beforeCount =ap.getRecordCount();

        ap.searchByUsername("Admin");

        int afterCount =ap.getRecordCount();

        Assert.assertNotEquals(beforeCount,afterCount);
        ap.validateSearchResult();
    }

    // TC-03
    @Test(priority = 3)
    public void searchByRoleTest() {
    	ap.refreshPage();

        ap.clickAdmin();

        ap.searchByUserRole();

        ap.validateSearchResult();
    }

    // TC-04
    @Test(priority = 4)
    public void searchByStatusTest(){

    	ap.refreshPage();
        ap.clickAdmin();

        ap.searchByStatus();

        ap.validateSearchResult();
    }
}