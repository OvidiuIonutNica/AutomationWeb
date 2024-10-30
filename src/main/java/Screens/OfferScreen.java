package Screens;

import org.openqa.selenium.By;
import org.testng.Assert;
import util.ActionsUtils;
import util.reporting.ExtentHelper;

import java.util.Set;

import static java.time.Duration.ofSeconds;
import static util.ActionsUtils.*;

public class OfferScreen {

    private final By closeBtnTranslationPopUp = By.cssSelector("path[d='m6 6 20 20M26 6 6 26']");
    private final By guestsField = By.xpath("//div[@id='GuestPicker-book_it-trigger']");
    private final By poolAmenities = By.xpath("//div[normalize-space()='Pool']");

    public OfferScreen checkIfOfferIsFor(String numberOfGuests) {
        ExtentHelper.logInfoEvent("Check if offer is for: " + numberOfGuests);

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        allWindows.remove(parentWindow);
        String childWindow = allWindows.iterator().next();
        driver.switchTo().window(childWindow);

        clickToElement(closeBtnTranslationPopUp);
        scrollToElement(guestsField, ofSeconds(10));
        Assert.assertTrue(ActionsUtils.isElementVisible(
                By.xpath("//span[normalize-space()='" + numberOfGuests + "']"),
                ofSeconds(5)), "This offer is not for 3 guests !");

        driver.close();
        driver.switchTo().window(parentWindow);
        return this;
    }

    public OfferScreen checkIfPoolIsPresentInAmenities() {
        ExtentHelper.logInfoEvent("Check if pool facility is present in offer");

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        allWindows.remove(parentWindow);
        String childWindow = allWindows.iterator().next();
        driver.switchTo().window(childWindow);

        Assert.assertTrue(isElementVisible((poolAmenities), ofSeconds(10)),
                "Pool facility is not present in offer !");

        driver.close();
        driver.switchTo().window(parentWindow);
        return this;
    }

    public OfferScreen checkIfPoolIsPresentInAmenitiesPopUp() {
        ExtentHelper.logInfoEvent("Check if pool facility is present Amenities pop-up");

        ExtentHelper.logInfoEvent("I don't know how to open pop-up");

        return this;
    }
}