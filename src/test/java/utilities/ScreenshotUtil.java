package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver,String fileName) throws IOException {

	    String timestamp =String.valueOf(System.currentTimeMillis());

	    String path =System.getProperty("user.dir")+ "\\Screenshots\\"+ fileName +timestamp+ ".png";

	    File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	    FileUtils.copyFile(src,new File(path));

	    return path;
	}
}