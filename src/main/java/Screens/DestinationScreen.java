package Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.ActionsUtils;
import util.DateUtils;
import util.reporting.ExtentHelper;

import java.util.List;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static util.ActionsUtils.*;
import static util.WaitUtils.waitFor;
import static util.WaitUtils.waitForElementToBeVisible;

public class DestinationScreen {

    private final By searchFilterContainer = By.xpath("//div[@role='search']");
    private final By searchFilterDiv = By.xpath(".//div");
    private final By firstOffer = By.cssSelector("[itemprop='itemListElement']:first-child a");

    private final By filterBtn = By.xpath("//span[text()='Filters']");
    private final By showMoreRollUp = By.xpath("//span[text()='Show more']");
    private final By poolFacilities = By.xpath("//span[normalize-space()='Pool']");
    private final By applyFilterBtn = By.xpath("//div[@class='ptiimno atm_7l_1p8m8iw dir dir-ltr']");
    private final By numberOfOffersText = By.cssSelector("span[data-testid=\"stays-page-heading\"]");
    private final By typeOfPlaceText = By.xpath("//span[contains(text(), 'Type of place')]");
    private final By plusBtn = By.xpath("//span[contains(@class, 'i98ho2o')]");

    public DestinationScreen checkIfFilterIsApplied() {
        ExtentHelper.logInfoEvent("Check if Filter is Applied");
        waitForElementToBeVisible(firstOffer);

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

    public DestinationScreen chosePoolFacilities() {
        ExtentHelper.logInfoEvent("Chose pool facilities");
        clickOnElementUsingJavaScriptExecutor(poolFacilities);
        return this;
    }

    public DestinationScreen applyAdvancedFilters() {
        ExtentHelper.logInfoEvent("Apply advanced filters");
        clickToElement(applyFilterBtn);
        return this;
    }

    public DestinationScreen setFiveBedrooms() {
        ExtentHelper.logInfoEvent("Set Five Bedrooms");
        waitForElementToBeVisible(typeOfPlaceText);
        List<WebElement> elements = driver.findElements(plusBtn);
        WebElement secondElement = elements.get(1);

        for (int i = 0; i < 5; i++) {
            waitFor(ofMillis(500));
            clickOnElementUsingJavaScriptExecutor(secondElement);
        }

        /* This method is very unstable
        pressKey(Keys.TAB, 12);
        pressKey(Keys.ENTER, 5);
         */
        return this;
    }

    public DestinationScreen checkNumberOfOffers() {
        waitFor(ofSeconds(3));
        String textWithNumberOfOffers = getTextFromElementWithWait(numberOfOffersText);
        String[] text = textWithNumberOfOffers.split(" ");
        int number = Integer.parseInt(text[0]);
        if (!(number >= 1)) {
            Assert.fail("There are no offers available with the applied filters! Display message present in page is: "
                    + textWithNumberOfOffers);
        }
        return this;
    }

    public DestinationScreen checkOffersContainNumberOfBedrooms() {
        List<String> offers = getTextFromVisibleElementsWithWait(By.xpath("//div[@data-testid='listing-card-subtitle']//span[contains(text(), 'bedrooms')]"));

        for (String offer : offers) {
            String[] textOffer = offer.split(" ");
            int bedrooms = Integer.parseInt(textOffer[0]);

            if (!(bedrooms >= 5)) {
                Assert.fail("The offers presented on the page do not have at least 5 bedrooms");
            }
        }
        return this;
    }

    public DestinationScreen checkFirstOfferWithHover() {
        hoverOverElement(firstOffer, ofSeconds(30));
        return this;
    }
}