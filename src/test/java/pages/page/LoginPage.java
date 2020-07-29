package pages.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[@data-link-action = 'display-register-form']")
    private WebElement registrationButton;

    @FindBy(xpath = "(//input[@name ='email'])[1]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name ='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-link-action = 'sign-in']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='help-block']")
    private WebElement errorField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage clickOnRegistrationButton(WebDriver driver){
        waitToBeClickable(registrationButton);
        registrationButton.click();
        return new RegistrationPage(driver);
    }

    public void sendKeysToEmailField(String email){
        waitToBeClickable(emailField);
        emailField.sendKeys(email);
    }

    public void sendKeysToPasswordField(String pass){
        waitToBeClickable(passwordField);
        passwordField.sendKeys(pass);
    }

    public void clickOnSignInButton(){
        waitToBeClickable(signInButton);
        signInButton.click();
    }

    public String getMassageFromError(){
        log.info("get Error message");
        waitToBeVisible(errorField);
        return errorField.getText();
    }
}
