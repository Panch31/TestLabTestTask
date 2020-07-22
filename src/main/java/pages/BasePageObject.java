package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject extends LoadableComponent<BasePageObject> {

    private Integer WAIT_TIME_IN_SECONDS = 5;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ElementLocatorFactory rootFactory;

    public BasePageObject(WebDriver driver) {
        init(driver);
        rootFactory = new DefaultElementLocatorFactory(driver);
        initialize();
    }

    public BasePageObject(WebDriver driver, By root) {
        init(driver);
        initElementsUnderRoot(waitForElementToAppearCustomWaitTime(root, WAIT_TIME_IN_SECONDS * 5));
    }

    public BasePageObject(WebDriver driver, WebElement root) {
        init(driver);
        initElementsUnderRoot(root);
    }

    public final BasePageObject initialize() {
        PageFactory.initElements(rootFactory, this);
        return this.get();
    }

    @Override
    protected void load() {
        PageFactory.initElements(rootFactory, this);
    }

    @Override
    protected void isLoaded() {

    }

    protected WebDriver getDriver() {
        return driver;
    }

    private void init(WebDriver driver) {
        this.driver = driver;
    }

    private void initElementsUnderRoot(WebElement root) {
        rootFactory = new DefaultElementLocatorFactory(root);
        initialize();
    }

    public final WebElement waitForElementToAppearCustomWaitTime(By by, int waitTimeInSecond) {
        for (int i = 0; i < 2; i++ )
            try {
                WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecond / 2);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (StaleElementReferenceException e) {
                waitFor(WAIT_TIME_IN_SECONDS * 100);
            } catch (TimeoutException e) {
            }
        throw new AssertionError(String.format("Element did not appear: %s", by));
    }

    protected final WebElement waitForElementToAppear(WebElement element) {
        final int countOfSearch = 4;
        for(int i = 0; i < countOfSearch; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SECONDS/countOfSearch);
                return wait.until(ExpectedConditions.visibilityOf(element));
            } catch (StaleElementReferenceException e) {
                i++;
                throw new Error("Element did not appear (SERE)");
            } catch (TimeoutException e) {
                i++;
            }
        }
        throw new AssertionError(String.format("Element did not appear: %s", element));
    }

    protected final void waitFor(Integer milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e1) {

        }
    }


}
