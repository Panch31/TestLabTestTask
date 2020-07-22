package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    private static final Logger log = Logger.getLogger(String.valueOf(HomePage.class));

    @FindBy(xpath = "(//span[contains(@class, 'expand-more')])[2]")
    private WebElement currencyField;

    @FindBy(xpath = "//span[@itemprop = 'price']")
    private List<WebElement> productsPriceCurrencyList;

    @FindBy(xpath = "(//i[@class = 'material-icons expand-more'])[2]")
    private WebElement currencyChangeButton;

    @FindBy(xpath = "//ul[@aria-labelledby = 'dLabel']/li[3]")
    private WebElement usdCurrency;

    @FindBy(xpath = "//input[@name = 's']")
    private WebElement searchField;

    public void goToLink(String http){
        driver.get(http);
    }

    public List getCurrencyProductList(){
        log.info("get product price currency list");
        waitToBeVisible(productsPriceCurrencyList.get(1));
        return productsPriceCurrencyList.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    public String getTextFromCurrencyField(){
        log.info("get currency");
        return currencyField.getText();
    }

    public void clickOnCurrencyChangeButton(){
        log.info("click on currency button");
        currencyChangeButton.click();
    }

    public void clickOnUsdCurrency(){
        log.info("click on usd currency");
        waitToBeClickable(usdCurrency);
        usdCurrency.click();
    }

    public void searchByWord(String searchedText){
        log.info("search by word");
        searchField.sendKeys(searchedText);
        searchField.sendKeys(Keys.ENTER);
    }

}
