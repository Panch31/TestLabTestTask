import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchPage;
import util.PropertyLoader;
//import util.TestListener;

import java.io.File;
import java.util.concurrent.TimeUnit;

//@Listeners(util.TestListener.class)
public class TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;
    public HomePage homePage;
    public SearchPage searchPage;

    @BeforeMethod
    public void beforeMethod() {
    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeSuite
    public void beforeSuite() {
        File chromeDriver = new File(PropertyLoader.loadProperty("chrome.driver.path"));
        ChromeDriverService driverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(chromeDriver)
                .usingAnyFreePort()
                .build();
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--start-maximized");
        driver = new ChromeDriver(driverService, chromeOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
    }

    public static WebDriver getWebDriver(){
        return driver;
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
    }

    @AfterSuite
    public void afterSuite() {
        driver.quit();
        System.out.println("afterSuite");
    }

}
