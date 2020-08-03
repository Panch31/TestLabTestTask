package pages.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCardsPanel extends BasePageObject {

    private static By root = By.xpath("//div[@class='products']");

    @FindBy(xpath = "//article[contains(@class , 'product')]")
    private List<WebElement> productsCardList;

    public ProductCardsPanel(WebDriver driver) {
        super(driver);
    }

    public List<Products> getAllProducts(){
        return productsCardList.stream().map(e -> new Products(driver,e)).collect(Collectors.toList());
    }

    public Products getProductByName(String name){
        return getAllProducts().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public class Products extends BasePageObject {

        @FindBy(xpath = "//article[contains(@class , 'product')]")
        private WebElement productCard;

        public Products(WebDriver driver, WebElement root) {
            super(driver, root);
        }

        public String getName(){
            return waitToBeVisible(productCard).getText().trim();
        }

    }

}
