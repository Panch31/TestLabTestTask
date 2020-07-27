package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    private static final Logger log = Logger.getLogger(String.valueOf(SearchPage.class));

    @FindBy(xpath = "//div[contains(@class, 'col-md-6')]/p")
    private WebElement searchedForProductCount;

    @FindBy(xpath = "//span[@class = 'discount-percentage']")
    private List<WebElement> discountOfProductWithDiscount;

    @FindBy(xpath = "//span[@class = 'discount-percentage']/preceding-sibling::span")
    private List<WebElement> productWithDiscountRegularPrice;

    @FindBy(xpath = "//span[@class = 'discount-percentage']/following-sibling::span")
    private List<WebElement> productWithDiscountPriceWithDiscount;

    @FindBy(xpath = "//div[@class = 'product-price-and-shipping']")
    private List<WebElement> searchedProductsList;

    @FindBy(xpath = "//div[@class = 'product-price-and-shipping']/span[1]")
    private List<WebElement> productRegularPriceList;

    @FindBy(xpath = "//div[@class = 'product-price-and-shipping']/span[contains(@itemprop, 'price')]")
    private List<WebElement> searchedProductsListPrice;

    @FindBy(xpath = "(//i[@class = 'material-icons pull-xs-right'])")
    private WebElement sortingButton;

    @FindBy(xpath = "(//div[@class = 'dropdown-menu']/a)[last()]")
    private WebElement fromHighToLowPriceField;

    public SearchPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
//        this.driver = driver;
//        wait = new WebDriverWait(driver, 10);
    }


    public void clickOnSortingButton() {
        log.info("click on the sorting field");
        waitToBeClickable(sortingButton).click();
    }

    public void clickOnFromHighToLowSortingField() {
        log.info("click on the sorting from High to Low price field");
        waitToBeVisible(fromHighToLowPriceField).click();
    }

    public String[] getTextAndIntFromSearchedForField() {
        String searchedFor = searchedForProductCount.getText();
        String[] searchedForString = searchedFor.split(":");
        return searchedForString;
    }

    public int productResultCount() {
        int productResultCount = searchedProductsList.size();
        return productResultCount;
    }

    public List<String> productResultListCurrency() {
        List<WebElement> resultListCurrency = searchedProductsListPrice;
        return resultListCurrency.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    public void setSorting() {
        clickOnSortingButton();
        clickOnFromHighToLowSortingField();
    }

    public List<Double> pricesList() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> regularPriceStringList = productRegularPriceList.stream().map(p -> p.getText().
                replace(",", ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<Double> regularPriceDoubleList = regularPriceStringList.stream().map(p -> Double.parseDouble(p))
                .collect(Collectors.toList());
        return regularPriceDoubleList;
    }

    public List getDiscountOfSaleProduct() {
        List<String> discountOfSaleProductStringList = discountOfProductWithDiscount.stream().map(p -> p.getText().
                replace("-", "").replace("%", "")).collect(Collectors.toList());
        List<Double> discountOfSaleProductDoubleList = discountOfSaleProductStringList.stream().map(p -> Double.parseDouble(p))
                .collect(Collectors.toList());
        return discountOfSaleProductDoubleList;
    }

    public List getDiscountProductRegularPrice() {
        List<String> discountOfProductRegularPriceStringList = productWithDiscountRegularPrice.stream().map(p -> p.getText().
                replace(",", ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<Double> discountOfSaleProductDoubleList = discountOfProductRegularPriceStringList.
                stream().map(p -> Double.parseDouble(p)).collect(Collectors.toList());
        return discountOfSaleProductDoubleList;
    }

    public List getDiscountProductPriceWithDiscount() {
        List<String> priceWithDiscountStringList = productWithDiscountPriceWithDiscount.stream().map(p -> p.getText().
                replace(",", ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<Double> priceWithDiscountDoubleList = priceWithDiscountStringList.stream().map(p -> Double.parseDouble(p))
                .collect(Collectors.toList());
        return priceWithDiscountDoubleList;
    }

}
