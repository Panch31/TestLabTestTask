//package helpers;
//
//import managers.AppManager;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.BeforeMethod;
//import pages.PageManager;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//public class SearchPageHelper extends PageManager {
//
//    public SearchPageHelper() {
//        super(AppManager.getWebDriver());
//    }
//
//    public String[] getTextAndIntFromSearchedForField(){
//        String searchedFor = searchPage.getTextFromSearchedForField();
//        String[] searchedForString = searchedFor.split(":");
//        return searchedForString;
//    }
//
//    public int productResultCount(){
//        int productResultCount = searchPage.getSearchedProductList().size();
//        return productResultCount;
//    }
//
//    public List<String> productResultListCurrency(){
//        List<WebElement> resultListCurrency = searchPage.getSearchedProductListPrice();
//        return resultListCurrency.stream().map(p -> p.getText()).collect(Collectors.toList());
//    }
//
//    public void setSorting(){
//        searchPage.clickOnSortingButton();
//        searchPage.clickOnFromHighToLowSortingField();
//    }
//
//    public List<Double> pricesList(){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<WebElement> productRegularPriceList = searchPage.getProductRegularPriceList();
//        List<String> regularPriceStringList = productRegularPriceList.stream().map(p -> p.getText().
//                replace("," , ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
//        List<Double> regularPriceDoubleList = regularPriceStringList.stream().map(p -> Double.parseDouble(p))
//        .collect(Collectors.toList());
//        return regularPriceDoubleList;
//    }
//
//    public List getDiscountOfSaleProduct(){
//        List<WebElement> discountOfSaleProduct = searchPage.getDiscountOfSaleProduct();
//        List<String> discountOfSaleProductStringList = discountOfSaleProduct.stream().map(p -> p.getText().
//                replace("-","").replace("%","")).collect(Collectors.toList());
//        List<Double> discountOfSaleProductDoubleList = discountOfSaleProductStringList.stream().map(p -> Double.parseDouble(p))
//                .collect(Collectors.toList());
//        return discountOfSaleProductDoubleList;
//    }
//
//    public List getDiscountProductRegularPrice(){
//        List<WebElement> discountOfProductRegularPrice = searchPage.getDiscountProductRegularPrice();
//        List<String> discountOfProductRegularPriceStringList = discountOfProductRegularPrice.stream().map(p -> p.getText().
//                replace("," , ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
//        List<Double> discountOfSaleProductDoubleList = discountOfProductRegularPriceStringList.
//                stream().map(p -> Double.parseDouble(p)).collect(Collectors.toList());
//        return discountOfSaleProductDoubleList;
//    }
//
//    public List getDiscountProductPriceWithDiscount(){
//        List<WebElement> priceWithDiscount = searchPage.getDiscountProductPriceWithDiscount();
//        List<String> priceWithDiscountStringList = priceWithDiscount.stream().map(p -> p.getText().
//                replace("," , ".").replaceAll("[^\\d.]", "")).collect(Collectors.toList());
//        List<Double> priceWithDiscountDoubleList = priceWithDiscountStringList.stream().map(p -> Double.parseDouble(p))
//                .collect(Collectors.toList());
//        return priceWithDiscountDoubleList;
//    }
//}
