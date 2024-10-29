package Screens;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitUtils;
import util.reporting.ExtentHelper;

import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static util.ActionsUtils.*;
import static util.DateUtils.getCurrentDate;
import static util.DateUtils.getLocalTimePlusDaysShortFormat;

@Slf4j
public class HomeScreen {

    private final By searchDestinationField = By.cssSelector("#bigsearch-query-location-input");
    private final By homeBtn = By.xpath("//a[@aria-label='Airbnb homepage']");
    private final By checkInField = By.xpath("//div[contains(text(), 'Add dates')]");

    public HomeScreen navigateToHomeScreen() {
        log.info("Navigate to home screen");
        clickToElement(homeBtn);
        return this;
    }

    public HomeScreen insertDestination(String destination) {
        ExtentHelper.logInfoEvent("Insert destination: " + destination);
        sendTextToElement(searchDestinationField, destination);
        return this;
    }

    public HomeScreen clickSearchBtn() {
        ExtentHelper.logInfoEvent("Click on Search button");
        clickButtonByText("Search");
        return this;
    }

    public HomeScreen setCheckIn() {
        ExtentHelper.logInfoEvent("Set Check In - current date");
        openDatePicker(0);
        String getCurrentDate = getCurrentDate();
        selectDate(getCurrentDate);
        return this;
    }

    public HomeScreen setCheckOut() {
        ExtentHelper.logInfoEvent("Set Check Out - after three days");
        String getThreeDaysAfterCurrentDate = getLocalTimePlusDaysShortFormat(3);
        selectDate(getThreeDaysAfterCurrentDate);
        return this;
    }

    public void openDatePicker(int index) {
        List<WebElement> datePickers = driver.findElements(checkInField);
        if (index < 0 || index >= datePickers.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        WebElement datePicker = datePickers.get(index);
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(datePicker));
        datePicker.click();
    }

    public void selectDate(String date) {
        ExtentHelper.logInfoEvent("Select Date: " + date);
        By dateElement = By.xpath("//div[@data-testid='" + date + "']");
        new WebDriverWait(driver, ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(dateElement));
        driver.findElement(dateElement).click();
    }

    public HomeScreen setGuests() {
        ExtentHelper.logInfoEvent("Move to Add Guests");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        WaitUtils.waitFor(ofSeconds(1));
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.ENTER).perform();

        ExtentHelper.logInfoEvent("Set Guests");
        ExtentHelper.logInfoEvent("Set two adults");
        actions.sendKeys(Keys.TAB).perform();
        WaitUtils.waitFor(ofMillis(200));
        actions.sendKeys(Keys.ENTER).perform();
        WaitUtils.waitFor(ofMillis(200));
        actions.sendKeys(Keys.ENTER).perform();

        ExtentHelper.logInfoEvent("Set one children");
        WaitUtils.waitFor(ofMillis(200));
        actions.sendKeys(Keys.TAB).perform();
        WaitUtils.waitFor(ofMillis(200));
        actions.sendKeys(Keys.ENTER).perform();
        return this;
    }
}
