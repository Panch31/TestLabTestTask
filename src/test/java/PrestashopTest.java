import org.testng.Assert;
import org.testng.annotations.Test;
import util.Url;


import java.util.List;
import java.util.logging.Logger;

public class PrestashopTest extends TestBase {

    private static final Logger log = Logger.getLogger(String.valueOf(PrestashopTest.class));

    @Test
    public void productsCurrencyAndHeadCurrencyTest(){
        log.info("go to prestashop site");
        homePage.goToLink("http://prestashop-automation.qatestlab.com.ua/ru/");
//        homePage.goToLink(Url.HOMEPAGE.name());
//        homePage.goToLink("http://prestashop-automation.qatestlab.com.ua/ru/");
//        homePage.getCurrencyProductList().forEach(elem -> Assert.assertTrue(elem.toString()
//                .contains(homePage.getTextFromCurrencyField())));
        log.info("products currency and head currency test passed successfully");
    }

    @Test
    public void searchedForCountTest(){
        log.info("go to prestashop site");
        homePage.goToLink("http://prestashop-automation.qatestlab.com.ua/ru/");
        homePage.clickOnCurrencyChangeButton();
        homePage.clickOnUsdCurrency();
        homePage.searchByWord("dress");
        String[] searchedForField = searchPage.getTextAndIntFromSearchedForField();
        String number = searchedForField[1].replace(" ", "").replace(".", "");
        int searchedForCount = Integer.parseInt(number);
        Assert.assertEquals(searchedForField[0], "Товаров");
        Assert.assertEquals(searchedForCount, searchPage.productResultCount());
        log.info("count of product that was searching by word test passed");
    }

    @Test
    public void currencyOfProductsAtSearchPageTest(){
        searchPage.productResultListCurrency().forEach(elem -> Assert.assertTrue(elem.contains("$")));
        log.info("test that product currency are the same that in head currency passed");
    }

    @Test
    public void productSortingTest(){
        searchPage.setSorting();
        List<Double> pricesList = searchPage.pricesList();
        for (int i = 0; i < searchPage.productResultCount(); i++){
            Assert.assertTrue(pricesList.get(i) >= pricesList.get(i++));
        }
        log.info("test that product are sorting passed");
    }

    @Test
    public void discountTest(){
        List<Double> discountOfSaleProduct = searchPage.getDiscountOfSaleProduct();
        List<Double> regularPriceOfDiscountProduct = searchPage.getDiscountProductRegularPrice();
        List<Double> priceWithDiscount = searchPage.getDiscountProductPriceWithDiscount();
        for (int i = 0; i < discountOfSaleProduct.size(); i++){
            double priceAfterDiscount = regularPriceOfDiscountProduct.get(i) - regularPriceOfDiscountProduct.get(i) *
                    discountOfSaleProduct.get(i) / 100;
            double roundPriceAfterDiscount = (double)Math.round(priceAfterDiscount * 100d) / 100d;
            double priceWithDiscountOnTheSite = priceWithDiscount.get(i);
            Assert.assertEquals(roundPriceAfterDiscount, priceWithDiscountOnTheSite);
        }
        log.info("test that discount is displayed in right way passed");
    }




}
