package tests;

import enums.DriverType;
import enums.Url;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePageObject;
import pages.page.*;
import pages.panel.LoginPanel;
import pages.panel.RegistrationPanel;
import util.DriverManager;
import util.DriverManagerFactory;
import util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

public class PrestaShopLoginTest extends TestBase {

    private List<String> passwordList = new ArrayList<>();
    private List<String> emailList = new ArrayList<>();

    @BeforeTest
    public void registrationAsUser() {
        try {
            driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
            log.info("1");
            driver = driverManager.getDriver();
            log.info("2");
            wait = new WebDriverWait(driver, 15);
            log.info("3");
            log.info("driver beforeMethodStarted started");
            String pass = PasswordGenerator.generateRandomPassword(10);
            log.info("1");
            HomePage homePage = goToLink();
            log.info("2");
            LoginPage loginPage = homePage.clickOnLoginButton();
            log.info("3");
            LoginPanel loginPanel = loginPage.getLoginPanel();
            RegistrationPage registrationPage = loginPanel.clickOnRegistrationButton();
            RegistrationPanel registrationPanel = registrationPage.getRegistrationPanel();
            registrationPanel.sendKeysToFirstNameField("Yevhenii");
            log.info("Yevhenii");
            registrationPanel.sendKeysToLastNameField("Panchenko");
            log.info("Panchenko");
            registrationPanel.sendKeysToPasswordField(pass);
            log.info("pass send");
            String email = registrationPanel.sendKeysToEmailField();
            log.info("email send");
            passwordList.add(pass);
            log.info("pass put to list");
            emailList.add(email);
            log.info("email put to list");
            registrationPanel.clickOnSaveButton();
            log.info("save");
            log.info("driver out");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void passwordChangeTest() {
        HomePage homePage = goToLink();
        LoginPage loginPage = homePage.clickOnLoginButton();
        LoginPanel loginPanel = loginPage.getLoginPanel();
        loginPanel.sendKeysToEmailField(emailList.get(0));
        loginPanel.sendKeysToPasswordField(passwordList.get(0));
        log.info("click on sign in button");
        loginPanel.clickOnSignInButton();
        log.info(emailList.get(0));
        log.info(passwordList.get(0));
        UserPage userPage = homePage.clickOnUserButton();
        UserInformationPage userInformationPage = userPage.clickOnUserInformationButton();
        String newPass = PasswordGenerator.generateRandomPassword(10);
        userInformationPage.changePassword(passwordList.get(0), newPass);
        userInformationPage.clickSaveButton();
        userInformationPage.clickOnLogOutButton();
        loginPanel = loginPage.getLoginPanel();
        log.info("1");
        loginPanel.sendKeysToEmailField(emailList.get(0));
        log.info("2");
        loginPanel.sendKeysToPasswordField(passwordList.get(0));
        log.info("3");
        loginPanel.clickOnSignInButton();
        log.info("4");
        loginPanel = loginPage.getLoginPanel();
        Assert.assertEquals(loginPanel.getMassageFromError(), "Ошибка авторизации.");
        log.info("first check with invalid password passed");
        loginPanel.sendKeysToPasswordField(newPass);
        loginPanel.clickOnSignInButton();
        Assert.assertEquals(userInformationPage.getTextFromHeader(), "Ваша персональная информация");
        log.info("second check with valid password passed");
    }

    @AfterTest
    public void cleanLists() {
        emailList.clear();
        passwordList.clear();
    }

}
