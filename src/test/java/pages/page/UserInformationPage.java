package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserInformationPage extends BasePage {

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement oldPasswordField;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement newPasswordField;

    @FindBy(xpath = "//a[@class='logout hidden-sm-down']")
    private WebElement logOutButton;

    @FindBy(xpath = "//header[@class='page-header']")
    private WebElement headerField;

    @FindBy(xpath = "//button[@data-link-action='save-customer']")
    private WebElement saveButton;

    public UserInformationPage(WebDriver driver) {
        super(driver);
    }

    public void changePassword(String oldPass, String newPass) {
        log.info("sendKeysOldPassword");
        waitToBeVisible(oldPasswordField).sendKeys(oldPass);
        log.info("sendKeysNewPassword");
        waitToBeVisible(newPasswordField).sendKeys(newPass);

    }

    public void clickOnLogOutButton() {
        log.info("click on userButton");
        waitToBeClickable(logOutButton).click();
    }

    public String getTextFromHeader() {
        log.info("getText from header");
        return headerField.getText();
    }

    public void clickSaveButton() {
        log.info("click on saveButton");
        waitToBeClickable(saveButton).click();
    }
}
