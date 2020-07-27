package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.page.SearchPage;
import pages.panel.SearchPanel;

import java.util.List;

public class PrestashopTestDiscount extends TestBase {

    @AfterMethod
    public void after(){
        SearchPanel searchPanel = getSearchPanel();
        searchPanel.cleanSearchField();
    }

    @Test
    public void productSortingTest() {
        log.info("3");
//        goToLink();
        SearchPage searchPage = getSearchPanel().searchByWord("dress");
        searchPage.setSorting();
        List<Double> pricesList = searchPage.pricesList();
        for (int i = 0; i < searchPage.productResultCount() - 1; i++) {
            Assert.assertTrue(pricesList.get(i) >= pricesList.get(i + 1));
        }
        log.info("test that product are sorting passed");
    }

    @Test
    public void discountTest() {
        log.info("4");
        SearchPage searchPage = getSearchPanel().searchByWord("dress");
        List<Double> discountOfSaleProduct = searchPage.getDiscountOfSaleProduct();
        List<Double> regularPriceOfDiscountProduct = searchPage.getDiscountProductRegularPrice();
        List<Double> priceWithDiscount = searchPage.getDiscountProductPriceWithDiscount();
        for (int i = 0; i < discountOfSaleProduct.size(); i++) {
            double priceAfterDiscount = regularPriceOfDiscountProduct.get(i) - regularPriceOfDiscountProduct.get(i) *
                    discountOfSaleProduct.get(i) / 100;
            double roundPriceAfterDiscount = (double) Math.round(priceAfterDiscount * 100d) / 100d;
            double priceWithDiscountOnTheSite = priceWithDiscount.get(i);
            Assert.assertEquals(roundPriceAfterDiscount, priceWithDiscountOnTheSite);
        }
        log.info("test that discount is displayed in right way passed");
    }
}