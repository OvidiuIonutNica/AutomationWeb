package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitFor(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementToBeVisible(By by) {
        FluentWait<WebDriver> wait = new FluentWait<>(ActionsUtils.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ClassCastException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeVisible(By by, int duration) {
        FluentWait<WebDriver> wait = new FluentWait<>(ActionsUtils.driver)
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ClassCastException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}