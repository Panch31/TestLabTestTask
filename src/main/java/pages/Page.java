package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.PropertyLoader;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Page extends BasePageObject {

    @FindBy(xpath = "//input[@name = 's']")
    private WebElement searchPanel;

    public Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() {
        waitForElementToAppear(searchPanel);
    }

    public MainSelectMenuPanel getMainSelectMenuPanel() {
        return new MainSelectMenuPanel(getDriver());
    }

    public void waitToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
