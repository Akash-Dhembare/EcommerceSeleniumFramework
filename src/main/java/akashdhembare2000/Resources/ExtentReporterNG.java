package akashdhembare2000.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path= System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter= new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Application Report");
        reporter.config().setDocumentTitle("");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Akash Dhembare");

        return extentReports;
    }
}
