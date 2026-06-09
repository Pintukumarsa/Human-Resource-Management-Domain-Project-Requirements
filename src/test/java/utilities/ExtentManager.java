//package utilities;
//
//import com.aventstack.extentreports.*;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentManager {
//
//    public static ExtentReports getReport() {
//
//        ExtentSparkReporter spark =new ExtentSparkReporter("./Reports/HRMReport.html");
//
//        ExtentReports extent =new ExtentReports();
//
//        extent.attachReporter(spark);
//
//        return extent;
//    }
//}


package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getReport() {

        if(extent == null) {

            ExtentSparkReporter spark =new ExtentSparkReporter("./Reports/HRMReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Pintu");
            extent.setSystemInfo("Project", "OrangeHRM");
        }

        return extent;
    }
}