package base;

import Screens.DestinationScreen;
import Screens.HomeScreen;
import Screens.OfferScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import util.ActionsUtils;

import static util.EnvUtils.BASE_URL;

public class BaseTest extends BaseExtentClass {

    public WebDriver driver;

    protected HomeScreen homeScreen;
    protected DestinationScreen destinationScreen;
    protected OfferScreen offerScreen;

    public BaseTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        ActionsUtils.driver = this.driver;
        homeScreen = new HomeScreen();
        destinationScreen = new DestinationScreen();
        offerScreen = new OfferScreen();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}