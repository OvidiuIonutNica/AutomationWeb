package util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static util.WaitUtils.waitFor;

@Slf4j
public class ActionsUtils {

    public static WebDriver driver;

    public static String getTextFromElement(By by) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public static String getTextFromElementWithWait(By by) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public static List<String> getTextFromVisibleElementsWithWait(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

        List<WebElement> elements = driver.findElements(by);
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                texts.add(element.getText());
            }
        }
        return texts;
    }

    public static void sendTextToElement(By by, String text) {
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(text);
    }

    public static void clickOnElementUsingJavaScriptExecutor(By by) {
        WebElement element = new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void clickOnElementUsingJavaScriptExecutor(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
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

    public static void pressKey(Keys key, int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            waitFor(ofMillis(500));
            actions.sendKeys(key).perform();
        }
    }

    public static void hoverOverElement(By element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement firstProperty = wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(firstProperty).perform();
    }
}