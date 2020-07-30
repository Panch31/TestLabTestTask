package pages.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;
import pages.page.RegistrationPage;

public class LoginPanel extends BasePageObject {

    private static By root = By.xpath("//section[@id='content']");

    public LoginPanel(WebDriver driver) {
        super(driver, root);
    }

    @FindBy(xpath = "//a[@data-link-action = 'display-register-form']")
    private WebElement registrationButton;

    @FindBy(xpath = "//input[@name ='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name ='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-link-action = 'sign-in']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='help-block']")
    private WebElement errorField;

    public RegistrationPage clickOnRegistrationButton() {
        waitToBeClickable(registrationButton);
        registrationButton.click();
        return new RegistrationPage(driver);
    }

    public void sendKeysToEmailField(String email) {
        waitToBeClickable(emailField);
        emailField.sendKeys(email);
    }

    public void sendKeysToPasswordField(String pass) {
        waitToBeClickable(passwordField);
        passwordField.sendKeys(pass);
    }

    public void clickOnSignInButton() {
        waitToBeClickable(signInButton);
        signInButton.click();
    }

    public String getMassageFromError() {
        log.info("get Error message");
        waitToBeVisible(errorField);
        return errorField.getText();
    }

}
