package tests;

import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.panel.ProductCardsPanel;
import pages.panel.SearchPanel;

public class ProductCardsTest extends TestBase {

    @Test
    public void testname() {
        HomePage homePage = goToLink();
        SearchPanel searchPanel = homePage.getSearchPanel();
        searchPanel.searchByWord("dress");
        ProductCardsPanel productCardsPanel = new ProductCardsPanel(driver);
        productCardsPanel.getAllProducts().forEach(e -> System.out.println(e.getName()));
    }
}
