package util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static java.time.Duration.ofSeconds;

@Slf4j
public class ActionsUtils {

    public static WebDriver driver;

    public static String getTextFromElement(By by) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public static void sendTextToElement(By by, String text) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFromElementWithWait(By by) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public static void clickOnElementUsingJavaScriptExecutor(By by) {
        WebElement element = new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void clickToElement(By by) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public static void clickButtonByText(String buttonText) {
        By button = By.xpath("//button[normalize-space()='" + buttonText + "']");
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(button));
        driver.findElement(button).click();
    }


    public static void scrollToElement(By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            log.warn("Element not visible after " + timeout.getSeconds() + " seconds.");
        }
    }

    public static void switchToTab(int tabPosition) {
        String currentTab = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabPosition));
    }

    public static boolean isElementVisible(By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(ActionsUtils.driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            log.warn("Element located using locator: {}", by);
            return false;
        }
    }
}

