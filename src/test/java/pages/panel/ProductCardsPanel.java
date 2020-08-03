package pages.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCardsPanel extends BasePageObject {

    private static By root = By.xpath(".//div[@class='products']");

    @FindBy(xpath = ".//article[contains(@class , 'product')]")
    private List<WebElement> productsCardList;

    public ProductCardsPanel(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getAllProducts() {
        return productsCardList.stream().map(e -> new ProductCard(driver, e)).collect(Collectors.toList());
    }


    public void getAllNames() {
        getAllProducts().forEach(p -> System.out.println(p.getName()));
    }

    public void getAllPrices() {
        getAllProducts().forEach(p -> System.out.println(p.getPrice()));
    }

    public void getCurrency() {
        getAllProducts().forEach(p -> System.out.println(p.getCurrency()));
    }


    public ProductCard getProductByName(String name) {
        return getAllProducts().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public class ProductCard extends BasePageObject {

        @FindBy(xpath = ".//h1")
        private WebElement productCardText;

        @FindBy(xpath = ".//span")
        private WebElement productCardPrice;

        public ProductCard(WebDriver driver, WebElement root) {
            super(driver, root);
        }

        public String getName() {
            return productCardText.getText().trim();
        }

        public String getPrice() {
            return productCardPrice.getText().replaceAll("[^0-9,]", "").trim();
        }

        public String getCurrency() {
            return productCardPrice.getText().trim();
        }

    }

}
