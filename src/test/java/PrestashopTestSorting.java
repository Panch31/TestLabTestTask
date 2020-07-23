import org.testng.Assert;
import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.page.SearchPage;

import java.util.logging.Logger;

public class PrestashopTestSorting extends TestBase {

    private static final Logger log = Logger.getLogger(String.valueOf(PrestashopTestSorting.class));

//    @Test
//    public void productsCurrencyAndHeadCurrencyTest(){
//        log.info("go to prestashop site");
//        homePage.goToLink("http://prestashop-automation.qatestlab.com.ua/ru/");
//        homePage.goToLink(Url.HOMEPAGE.name());
//        homePage.goToLink("http://prestashop-automation.qatestlab.com.ua/ru/");
//        homePage.getCurrencyProductList().forEach(elem -> Assert.assertTrue(elem.toString()
//                .contains(homePage.getTextFromCurrencyField())));
//        log.info("products currency and head currency test passed successfully");
//    }

    @Test
    public void searchedForCountTest() {
        log.info("1");
        log.info("go to prestashop site");
        HomePage homePage = goToLink();
        homePage.clickOnCurrencyChangeButton();
        homePage.clickOnUsdCurrency();
        getSearchPanel().searchByWord("dress");
        SearchPage searchPage = getSearchPage();
        String[] searchedForField = searchPage.getTextAndIntFromSearchedForField();
        String number = searchedForField[1].replace(" ", "").replace(".", "");
        int searchedForCount = Integer.parseInt(number);
        Assert.assertEquals(searchedForField[0], "Товаров");
        Assert.assertEquals(searchedForCount, searchPage.productResultCount());
        log.info("count of product that was searching by word test passed");
    }

    @Test
    public void currencyOfProductsAtSearchPageTest() {
        log.info("2");
        SearchPage searchPage = getSearchPage();
        searchPage.productResultListCurrency().forEach(elem -> Assert.assertTrue(elem.contains("$")));
        log.info("test that product currency are the same that in head currency passed");
    }


}
