package base;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
//        WaitUtils.waitForVisibilityBy(driver, By.xpath("//h5[text()='Login']"));
//        String path =ScreenshotUtil.captureScreenshot(driver,"LoginPage");
    }

    @AfterClass
    public void tearDown() {

        if(driver!=null) {
            driver.quit();
        }
    }
}