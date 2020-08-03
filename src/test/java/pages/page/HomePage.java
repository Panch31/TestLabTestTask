package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

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

    @FindBy(xpath = "(//span[@class = 'hidden-sm-down'])[1]")
    private WebElement loginButton;

    @FindBy(xpath = "(//span[@class='hidden-sm-down'])[1]")
    private WebElement userButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToLink(String http) {
        driver.get(http);
    }

    public List getCurrencyProductList() {
        log.info("get product price currency list");
        waitToBeVisible(productsPriceCurrencyList.get(1));
        return productsPriceCurrencyList.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    public String getTextFromCurrencyField() {
        log.info("get currency");
        return currencyField.getText();
    }

    public void clickOnCurrencyChangeButton() {
        log.info("click on currency button");
        waitToBeClickable(currencyChangeButton).click();
    }

    public void clickOnUsdCurrency() {
        log.info("click on usd currency");
        waitToBeClickable(usdCurrency).click();
    }

    public LoginPage clickOnLoginButton() {
        log.info("click on loginButton");
        waitToBeClickable(loginButton).click();
        return new LoginPage(driver);
    }

    public UserPage clickOnUserButton() {
        log.info("click on userButton");
        waitToBeClickable(userButton).click();
        return new UserPage(driver);
    }

    public WebElement getSearchField() {
        return searchField;
    }

}
