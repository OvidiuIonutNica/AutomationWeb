package util.reporting;

import com.aventstack.extentreports.Status;
import org.slf4j.Logger;

public enum ExtentHelper {
    INSTANCE;

    private static final Logger LOG = CustomLogger.INSTANCE.getLogger(ExtentHelper.class);

    private ExtentHelper() {
    }

    public static void logInfoEvent(String message) {
        LOG.info(message);

        try {
            ExtentTestManager.getTest().log(Status.INFO, message);
        } catch (NullPointerException var2) {
            LOG.warn(var2.getMessage());
        }
    }
}