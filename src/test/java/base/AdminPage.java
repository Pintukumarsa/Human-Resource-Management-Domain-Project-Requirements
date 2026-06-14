package base;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.WaitUtils;

public class AdminPage {

    WebDriver driver;

    public AdminPage(WebDriver driver) {

        this.driver = driver;
    }


    // LOCATORS
    
    By adminMenu =By.xpath("//nav[@role=\"navigation\" and @aria-label=\"Sidepanel\"]//*[text()=\"Admin\"]");

    By usernameTextbox = By.xpath("//*[*[label[text()=\"Username\"]]]//input");

    By searchButton =By.xpath("//button[@type='submit']");

    By resetButton =By.xpath("//button[@type='reset']");

    By recordsFound =By.xpath("//span[contains(.,'Record')]");
    
    By listOfUser = By.xpath("//div[contains(@class,'table-body') and @role=\"rowgroup\"]/div");

    By userRoleDropdown =By.xpath("//*[*[label[text()=\"User Role\"]]]//div[contains(@class,\"select-text-input\")]");

    By adminRole =By.xpath("//*[@role=\"listbox\"]//*[text()=\"Admin\"]");

    By statusDropdown =By.xpath("//*[*[label[text()=\"Status\"]]]//div[contains(@class,\"select-text-input\")]");

    By enabledStatus =By.xpath("//*[@role=\"listbox\"]//*[text()=\"Enabled\"]");
    By leftMenuItems =By.xpath("//*[@role=\"navigation\" and @aria-label=\"Sidepanel\"]//li");

    
    public void clickAdmin() {

        WaitUtils.waitForClickable(driver,adminMenu).click();
    }

    public int getLeftMenuCount() {

        List<WebElement> menuList =driver.findElements(leftMenuItems);

        return menuList.size();
    }

    public void searchByUsername(
            String username) {

        WaitUtils.waitForVisibilityBy(driver,usernameTextbox).clear();

        driver.findElement(usernameTextbox).sendKeys(username);

        driver.findElement(searchButton).click();

        WaitUtils.waitForVisibilityBy(driver,recordsFound);
    }

    public void searchByUserRole() {
    	

        WaitUtils.waitForClickable(driver,userRoleDropdown).click();

        WaitUtils.waitForClickable(driver,adminRole).click();

        driver.findElement(searchButton).click();

        WaitUtils.waitForVisibilityBy(driver,recordsFound);
        
    }

    public void searchByStatus() {

        WaitUtils.waitForClickable(driver,statusDropdown).click();

        WaitUtils.waitForClickable(driver,enabledStatus).click();

        driver.findElement(searchButton).click();

        WaitUtils.waitForVisibilityBy(driver,recordsFound);
    }

    public String getRecordText() {
    	 return WaitUtils.waitForClickable(driver,recordsFound).getText();
    }
    
    		// GET RECORD COUNT NUMBER
    public int getRecordCount() {

    	String resultText =driver.findElement(recordsFound).getText();	
    	
    // Output will "(1) Record Found" >> i need numeric value from that string
        String number =resultText.replaceAll("[^0-9]", "");
    	return Integer.parseInt(number);
    		   
   }

    
   //TOTAL TABLE ROWS (number of user present on the result table)
    		public int getTableRowCount() {
    		    return driver.findElements(listOfUser).size();
    		}
   
    		 
    		// Reusable Validation

         public void validateSearchResult() {

    	        int recordCount =getRecordCount();

    	        int tableCount =getTableRowCount();

    	        System.out.println("Record Count : "+ recordCount);

    	        System.out.println("Table Count : "+ tableCount);

    	        Assert.assertEquals(recordCount,tableCount);
    	        try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }

        public void refreshPage() {

        driver.navigate().refresh();
    }
}
