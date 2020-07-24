package pages.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;
import pages.page.SearchPage;

public class SearchPanel extends BasePageObject {


    private static By root = By.cssSelector(".header-top");

    public SearchPanel(WebDriver driver) {
        super(driver, root);
    }

    @FindBy(xpath = "//input[@name = 's']")
    private WebElement searchField;

    public SearchPage searchByWord(String word) {
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
        return new SearchPage(driver);
    }

}


