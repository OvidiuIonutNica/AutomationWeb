import base.BaseExtentClass;
import base.BaseTest;
import org.testng.annotations.Test;

import static util.ConstantsUtils.DESTINATION_ROME_ITALY;
import static util.ConstantsUtils.NUMBER_OF_GUESTS;

public class Tests extends BaseTest {

    @Test(description = "Verify that the results match the search criteria")
    public void verifyResultsMatchSearchCriteria() {
        homeScreen
                .insertDestination(DESTINATION_ROME_ITALY)
                .setCheckIn()
                .setCheckOut()
                .setGuests()
                .clickSearchBtn();
        destinationScreen
                .checkIfFilterIsApplied()
                .openFirstOffer()
                .checkIfOfferIsFor(NUMBER_OF_GUESTS);
    }
}
