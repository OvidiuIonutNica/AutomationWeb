package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;


public class BaseExtentClass extends Assert {

    private static ExtentReports extent;
    private static final ThreadLocal<Boolean> initializationStatus = new ThreadLocal<Boolean>() {
        protected Boolean initialValue() {
            return false;
        }
    };
    protected final Logger log;

    public BaseExtentClass() {
        this.log = util.reporting.CustomLogger.INSTANCE.getLogger(this.getClass());
    }

    public BaseExtentClass(Logger log) {
        this.log = log;
    }

    @BeforeMethod(
            alwaysRun = true
    )
    public void extentSetup(ITestContext context) {
        extent = util.reporting.ExtentManager.getReporter();
    }

    @BeforeMethod(
            alwaysRun = true
    )
    public void startExtent(Method method, Object[] dataProvider) {
        Test test = (Test) method.getAnnotation(Test.class);
        String var10000 = method.getName();
        String testName = var10000 + " " + this.getTestName(dataProvider);
        String description = test.description();

        if (dataProvider != null && dataProvider.length > 0 && dataProvider[0] instanceof String) {
            description += ", Data Provider: " + dataProvider[0];
        }

        util.reporting.ExtentTestManager.startTest(testName);
        util.reporting.ExtentTestManager.getTest().assignAuthor(new String[]{"General_status"});
        util.reporting.ExtentTestManager.getTest().getModel().setDescription(description);
    }

    private String getTestName(Object[] testData) {
        try {
            String[] dataProvider = (String[]) testData[0];
            return Arrays.toString(dataProvider);
        } catch (ClassCastException var3) {
            return String.valueOf(testData[0]);
        } catch (ArrayIndexOutOfBoundsException var4) {
            return "";
        }
    }

    @AfterMethod(
            alwaysRun = true
    )
    public void afterEachTestMethod(ITestResult result) {
        String[] var2 = result.getMethod().getGroups();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String group = var2[var4];
            util.reporting.ExtentTestManager.getTest().assignCategory(new String[]{group});
        }

        switch (result.getStatus()) {
            case 1 -> util.reporting.ExtentTestManager.getTest().log(Status.PASS, "Test Finished");
            case 2 -> util.reporting.ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
            case 3 -> Optional.ofNullable(result.getThrowable()).ifPresentOrElse((throwable) -> {
                util.reporting.ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped due to " + result.getThrowable().getMessage());
            }, () -> {
                util.reporting.ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped due to failing/skipping in before hooks of tests");
            });
            default -> Optional.ofNullable(result.getThrowable()).ifPresentOrElse((throwable) -> {
                util.reporting.ExtentTestManager.getTest().log(Status.WARNING, "Test status unknown due to " + result.getThrowable().getMessage());
            }, () -> {
                util.reporting.ExtentTestManager.getTest().log(Status.WARNING, "Test status Unknown due to preconditions or other dependencies");
            });
        }

        extent.flush();
    }

    @AfterSuite(
            alwaysRun = true
    )
    public void generateReport() {
        if (null != extent) {
            initializationStatus.set(false);
        }
    }
}