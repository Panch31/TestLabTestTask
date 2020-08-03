package tests;

import enums.DriverType;
import enums.Url;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.ListenersTestng;
import pages.page.HomePage;
import pages.page.SearchPage;
import pages.panel.SearchPanel;
import util.DriverManager;
import util.DriverManagerFactory;

import java.util.logging.Logger;

@Listeners(ListenersTestng.class)
public class TestBase {

    public DriverManager driverManager;
    public WebDriver driver;
    protected WebDriverWait wait;
    public final Logger log = Logger.getLogger(String.valueOf(TestBase.class));

    @BeforeMethod
    public void beforeMethod() {
        log.info("DRIVER CLASS STARTED");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @BeforeSuite
    public void beforeSuite() {
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("afterTestBase QUITEDRIVER");
        driverManager.quitDriver();
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {
    }

    public HomePage goToLink() {
        driver.get(Url.HOMEPAGE.getLink());
        return new HomePage(driver);
    }

    public SearchPanel getSearchPanel() {
        return new SearchPanel(driver);
    }

    public SearchPage getSearchPage() {
        return new SearchPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

}