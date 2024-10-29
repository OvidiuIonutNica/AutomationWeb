package util.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String workingDir = System.getProperty("user.dir");
            ExtentSparkReporter reporter = new ExtentSparkReporter(workingDir + "/ExtentReports/ExtentReportResults.html");
            reporter.config().setReportName("ChatBots API Automation");
            reporter.config().setDocumentTitle("Test report for API automation");
            reporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Author", "Ovidiu Nica");
        }
        return extent;
    }

    public static synchronized ExtentReports getExtentReports() {
        return extent;
    }
}