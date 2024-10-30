import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static util.ConstantsUtils.DESTINATION_ROME_ITALY;
import static util.ConstantsUtils.NUMBER_OF_GUESTS;

public class Tests extends BaseTest {

    @Test(description = "Test 1: Verify that the results match the search criteria")
    public void verifyResultsMatchSearchCriteria() {
        homeScreen
                .insertDestination(DESTINATION_ROME_ITALY)
                .setCheckIn()
                .setCheckOut()
                .setGuests()
                .clickSearchBtn();
        destinationScreen
                .checkIfFilterIsApplied()
                .openFirstOffer();
        offerScreen
                .checkIfOfferIsFor(NUMBER_OF_GUESTS);
    }

    @Test(description = "Test 2: Verify that the results and details page match the extra filters")
    public void verifyResultsAndDetailsPageMatchExtraFilters() {
        homeScreen
                .insertDestination(DESTINATION_ROME_ITALY)
                .setCheckIn()
                .setCheckOut()
                .setGuests()
                .clickSearchBtn();
        destinationScreen
                .checkIfFilterIsApplied()
                .openFilter()
                .setFiveBedrooms()
                .showMoreFacilities()
                .chosePoolFacilities()
                .applyAdvancedFilters()
                .checkNumberOfOffers()
                .checkOffersContainNumberOfBedrooms()
                .openFirstOffer();
        offerScreen
                .checkIfPoolIsPresentInAmenities()
                .checkIfPoolIsPresentInAmenitiesPopUp();
    }

    @Test(description = "Test 3: Verify that a property is displayed on the map correctly")
    public void verifyPropertyIsDisplayedCorrectlyOnMap() {
        homeScreen
                .insertDestination(DESTINATION_ROME_ITALY)
                .setCheckIn()
                .setCheckOut()
                .setGuests()
                .clickSearchBtn();
        destinationScreen
                .checkIfFilterIsApplied()
                .checkFirstOfferWithHover();
        /*
        With Selenium I don't know how I can check the color change after hovering
         */
    }

    @AfterMethod
    public void returnToHomeScreen() {
        homeScreen.navigateToHomeScreen();
    }
}