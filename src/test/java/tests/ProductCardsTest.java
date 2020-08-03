package tests;

import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.panel.ProductCardsPanel;
import pages.panel.SearchPanel;

import java.util.List;

public class ProductCardsTest extends TestBase {

    @Test
    public void testname() {
        HomePage homePage = goToLink();
        SearchPanel searchPanel = homePage.getSearchPanel();
        searchPanel.searchByWord("dress");
        ProductCardsPanel productCardsPanel = new ProductCardsPanel(driver);
        productCardsPanel.getCurrency();
        homePage.clickOnCurrencyChangeButton();
        homePage.clickOnUsdCurrency();
        productCardsPanel.getAllNames();
        productCardsPanel.getAllPrices();
        productCardsPanel.getCurrency();
    }
}
