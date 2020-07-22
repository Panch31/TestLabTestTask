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
import util.*;
//import util.TestListener;

import java.io.File;
import java.util.concurrent.TimeUnit;

//@Listeners(util.TestListener.class)
public class TestBase {

    public DriverManager driverManager;
    protected WebDriver driver;
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
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        wait = new WebDriverWait(driver, 15);
//        homePage = new HomePage(driver);
//        searchPage = new SearchPage(driver);
    }

    public HomePage goToLink(){
        driver.get(Url.HOMEPAGE.getLink());
        return new HomePage(driver);
    }

    public SearchPage findByWord(HomePage homePage, String searchedWord){
        homePage.searchByWord(searchedWord);
        return new SearchPage(driver);
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
        driverManager.quitDriver();
        System.out.println("afterSuite");
    }

}
