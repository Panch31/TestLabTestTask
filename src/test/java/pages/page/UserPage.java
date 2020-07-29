package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class UserPage extends BasePage {

    @FindBy(xpath = "(//i[@class='material-icons'])[2]")
    private WebElement userInformationButton;


    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnUserInformationButton() {
        log.info("click on userInformationButton");
        waitToBeClickable(userInformationButton).click();
    }
}
