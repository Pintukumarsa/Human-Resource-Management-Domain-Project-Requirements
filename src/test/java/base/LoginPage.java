package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.*;
public class LoginPage {

    WebDriver driver;

    By username =By.name("username");
    By password =By.name("password");
    By loginBtn =By.xpath("//button[@type='submit']");
    public By searchInputField =By.xpath("//input[@placeholder='Search']");
    public By invalidMessage = By.xpath("//p[contains(.,'Invalid credentials')]");
    By userProfileDropDown = By.xpath("//li[contains(@class,'userdropdown')]");
    By profile =By.xpath("//span[@class='oxd-userdropdown-tab']");
    public By logout =By.xpath("//a[text()='Logout']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user,String passwrd) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(passwrd);
        driver.findElement(loginBtn).click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public boolean isDashboardDisplayed() {
    	WaitUtils.waitForVisibilityBy(driver,userProfileDropDown);
        return driver.findElement(userProfileDropDown).isDisplayed();

    }
    
    public String invalidMessage() {
    	WaitUtils.waitForVisibilityBy(driver,invalidMessage);
        return driver.findElement(invalidMessage).getText();

    }
    public void logoutUser() {

        driver.findElement(profile).click();

        driver.findElement(logout).click();
        WaitUtils.waitForVisibilityBy(driver,username);
    }
    
    
  }