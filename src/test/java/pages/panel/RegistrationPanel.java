package pages.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;

import java.util.Random;

public class RegistrationPanel extends BasePageObject {

    private static By root = By.xpath("//section[@class='register-form']");

    public RegistrationPanel(WebDriver driver) {
        super(driver, root);
    }

    @FindBy(xpath = "//input[@name = 'firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name = 'lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "(//input[@name = 'email'])[1]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-link-action='save-customer']")
    private WebElement saveButton;


    public void sendKeysToFirstNameField(String firstName) {
        waitToBeClickable(firstNameField);
        firstNameField.sendKeys(firstName);
    }

    public void sendKeysToLastNameField(String lastName) {
        waitToBeClickable(lastNameField);
        lastNameField.sendKeys(lastName);
    }

    public String sendKeysToEmailField() {
        Random random = new Random();
        int randomEmail = random.nextInt(10000000);
        waitToBeClickable(emailField);
        String email = "test" + "+" + randomEmail + "@test.com";
        emailField.sendKeys(email);
        return email;
    }

    public void sendKeysToPasswordField(String pass) {
        waitToBeClickable(passwordField);
        passwordField.sendKeys(pass);
    }

    public void clickOnSaveButton() {
        waitToBeClickable(saveButton);
        saveButton.click();
    }
}
