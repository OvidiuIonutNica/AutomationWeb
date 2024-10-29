package util.reporting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CustomLogger {
    INSTANCE;

    final String consoleLogLevel = System.getProperty("consoleLogLevel");
    final String fileLogLevel = System.getProperty("fileLogLevel");
    private Logger log;

    private CustomLogger() {
    }

    public Logger getLogger(Class<?> clazz) {
        if (null == this.consoleLogLevel) {
            System.setProperty("consoleLogLevel", "INFO");
        }

        if (null == this.fileLogLevel) {
            System.setProperty("fileLogLevel", "DEBUG");
        }

        this.log = LoggerFactory.getLogger(clazz);
        return this.log;
    }
}