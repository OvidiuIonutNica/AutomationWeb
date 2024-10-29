package Screens;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.ActionsUtils;
import util.DateUtils;
import util.WaitUtils;
import util.reporting.ExtentHelper;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static util.ActionsUtils.*;

@Slf4j
public class DestinationScreen {

    private final By searchFilterContainer = By.xpath("//div[@role='search']");
    private final By searchFilterDiv = By.xpath(".//div");
    private final By firstOffer = By.cssSelector("[itemprop='itemListElement']:first-child a");
    private final By closeBtnTranslationPopUp = By.cssSelector("path[d='m6 6 20 20M26 6 6 26']");
    private final By guestsField = By.xpath("//div[@id='GuestPicker-book_it-trigger']");
    private final By filterBtn = By.xpath("//span[text()='Filters']");
    private final By showMoreRollUp = By.xpath("//span[text()='Show more']");
    private final By poolFacilities = By.xpath("//span[normalize-space()='Pool']");
    private final By applyFilterBtn = By.xpath("//div[@class='ptiimno atm_7l_1p8m8iw dir dir-ltr']");

    public DestinationScreen checkIfFilterIsApplied() {
        ExtentHelper.logInfoEvent("Check if Filter is Applied");
        WaitUtils.waitForElementToBeVisible(firstOffer);

        String city = "Rome";
        String date = DateUtils.getFormattedDate(3);
        String numberOfGuests = "3 guests";
        String[] divTextsExpected = {city, date, numberOfGuests};

        WebElement filterContainer = ActionsUtils.driver.findElement(searchFilterContainer);
        List<WebElement> divElements = filterContainer.findElements(searchFilterDiv);

        int numberOfFirstThreeDivs = 3;
        for (int i = 0; i < numberOfFirstThreeDivs; i++) {
            String divText = divElements.get(i).getText();
            Assert.assertEquals(divText, divTextsExpected[i],
                    "Expected text: " + divText + " ,is not equal with: " + divTextsExpected[i]);
        }
        return this;
    }

    public DestinationScreen openFirstOffer() {
        ExtentHelper.logInfoEvent("Open first offer present in page");
        ActionsUtils.clickOnElementUsingJavaScriptExecutor(firstOffer);
        return this;
    }

    public DestinationScreen checkIfOfferIsFor(String numberOfGuests) {
        ExtentHelper.logInfoEvent("Check if offer is for: " + numberOfGuests);
        switchToTab(1);
        clickToElement(closeBtnTranslationPopUp);
        scrollToElement(guestsField, ofSeconds(10));
        Assert.assertTrue(ActionsUtils.isElementVisible(
                By.xpath("//span[normalize-space()='" + numberOfGuests + "']"),
                ofSeconds(5)), "This offer is not for 3 guests !");
        switchToTab(0);
        return this;
    }

    public DestinationScreen openFilter() {
        ExtentHelper.logInfoEvent("Open Filter");
        clickOnElementUsingJavaScriptExecutor(filterBtn);
        return this;
    }

    public DestinationScreen showMoreFacilities() {
        ExtentHelper.logInfoEvent("Show more facilities");
        scrollToElement(showMoreRollUp, ofSeconds(30));
        clickOnElementUsingJavaScriptExecutor(showMoreRollUp);
        return this;
    }

    public DestinationScreen chosePoolFacilities(){
        ExtentHelper.logInfoEvent("Chose pool facilities");
        clickOnElementUsingJavaScriptExecutor(poolFacilities);
        return this;
    }

    public DestinationScreen applyAdvancedFilters(){
        ExtentHelper.logInfoEvent("Apply advanced filters");
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@href, 'Rome--Italy')]"));
        elements.get(5).click();
        return this;
    }

    public DestinationScreen setFiveBedrooms(){
        ExtentHelper.logInfoEvent("Set Five Bedrooms");
        pressKey(Keys.TAB, 12);
        pressKey(Keys.ENTER, 5);
        return this;
    }
}
