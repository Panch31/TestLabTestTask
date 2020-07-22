import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.page.BasePage;
import pages.page.HomePage;
import pages.page.SearchPage;
import pages.panel.SearchPanel;
import util.*;
//import util.TestListener;


//@Listeners(util.TestListener.class)
public class TestBase {

    public DriverManager driverManager;
    protected WebDriver driver;
    private static WebDriverWait wait;
//    public HomePage homePage;
//    public SearchPage searchPage;
//    public SearchPanel searchPanel;

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
    }

    public HomePage goToLink(){
        driver.get(Url.HOMEPAGE.getLink());
        return new HomePage(driver);
    }

    public SearchPanel getSearchPanel(){
        return new SearchPanel(driver);
    }

    public SearchPage searchPageRefresh(){
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
