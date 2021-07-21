package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {

    public static ExtentReports extent;
    public static ExtentTest extentTest;

    //This function is for making Extent Report
    public static ExtentTest Extent_Report() {

        String extentReportFile = System.getProperty("user.dir") + "/HTML_Report/ExtentReportFile.html";
        String extentReportImage = System.getProperty("user.dir")+ "/HTML_Report/ExtentReportImage.png";

        // Create object of extent report and specify the report file path.
        extent = new ExtentReports(extentReportFile, false);
        // Start the test using the ExtentTest class object.
        extentTest = extent.startTest("Calculate Trip Cost Test","Verify All Cases");

        return extentTest;

    }

    public static void endExtentTest() {

        // close report.
        extent.endTest(extentTest);

        // writing everything to document.
        extent.flush();


    }
}
