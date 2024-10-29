package util.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = ExtentManager.getReporter().createTest(testName);
        test.set(extentTest);
    }

    public static void log(Status status, String details) {
        getTest().log(status, details);
    }

    public static void endTest() {
        ExtentManager.getReporter().flush();
    }
}