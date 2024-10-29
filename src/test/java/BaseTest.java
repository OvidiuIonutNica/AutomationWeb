import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://www.airbnb.com/";

    public BaseTest() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
