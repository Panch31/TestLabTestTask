package tests;

import enums.DriverType;
import enums.Url;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.page.HomePage;
import pages.page.SearchPage;
import pages.panel.SearchPanel;
import util.DriverManager;
import util.DriverManagerFactory;


//@Listeners(util.TestListener.class)
public class TestBase {

    public DriverManager driverManager;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void beforeMethod() {
    }

    @BeforeClass
    public void beforeClass() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @BeforeSuite
    public void beforeSuite() {
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
        driverManager.quitDriver();
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


}
