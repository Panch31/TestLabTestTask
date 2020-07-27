package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.page.HomePage;
import pages.page.SearchPage;
import pages.panel.SearchPanel;

public class PrestashopTestCurrency extends TestBase {


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

    @BeforeMethod
    public void beforeMethod() {
        goToLink();
    }

    @AfterMethod
    public void after() {
        SearchPanel searchPanel = getSearchPanel();
        searchPanel.cleanSearchField();
    }

    @Test(priority = 1)
    public void searchedForCountTest() {
        log.info("1");
        log.info("go to prestashop site");
        HomePage homePage = goToLink();
        homePage.clickOnCurrencyChangeButton();
        homePage.clickOnUsdCurrency();
        SearchPage searchPage = getSearchPanel().searchByWord("dress");
        String[] searchedForField = searchPage.getTextAndIntFromSearchedForField();
        String number = searchedForField[1].replace(" ", "").replace(".", "");
        int searchedForCount = Integer.parseInt(number);
//        Assert.assertEquals(searchedForField[0], "Товаров");
        Assert.assertEquals(searchedForField[0], "alooooooo");
        Assert.assertEquals(searchedForCount, searchPage.productResultCount());
        log.info("count of product that was searching by word test passed");
    }

    @Test(priority = 2)
    public void currencyOfProductsAtSearchPageTest() {
        log.info("2");
//        getSearchPanel().cleanSearchField();
        getSearchPanel().searchByWord("dress").productResultListCurrency().forEach(elem -> Assert.assertTrue(elem.contains("$")));
        log.info("test that product currency are the same that in head currency passed");
    }


}