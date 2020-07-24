package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.page.SearchPage;
import tests.PrestashopTestCurrency;

import java.util.List;
import java.util.logging.Logger;

public class PrestashopTestDiscount extends TestBase {

    private static final Logger log = Logger.getLogger(String.valueOf(PrestashopTestCurrency.class));

    @Test
    public void productSortingTest() {
        log.info("3");
        HomePage homePage = goToLink();
        SearchPage searchPage = getSearchPanel().searchByWord("dress");
        searchPage.setSorting();
        List<Double> pricesList = searchPage.pricesList();
        for (int i = 0; i < searchPage.productResultCount()-1; i++) {
            Assert.assertTrue(pricesList.get(i) >= pricesList.get(i+1));
        }
        log.info("test that product are sorting passed");
    }

    @Test
    public void discountTest() {
        log.info("4");
        SearchPage searchPage = getSearchPage();
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
