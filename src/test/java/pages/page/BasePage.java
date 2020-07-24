package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;
import pages.panel.SearchPanel;

public class BasePage extends BasePageObject {

    @FindBy(xpath = "//input[@name = 's']")
    private WebElement searchPanel;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded(){
    }

    public SearchPanel getSearchPanel() {
        return new SearchPanel(getDriver());
    }

}
