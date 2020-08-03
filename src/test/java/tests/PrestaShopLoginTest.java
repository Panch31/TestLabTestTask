package tests;

import enums.DriverType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

    @BeforeClass
    public void registrationAsUser() {
        try (DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME)) {
            log.info("driver started");
            driver = driverManager.getDriver();
            log.info("webdriver started");
            wait = new WebDriverWait(driver, 15);
            String pass = PasswordGenerator.generateRandomPassword(10);
            log.info("go to home page");
            HomePage homePage = goToLink();
            log.info("login page open");
            LoginPage loginPage = homePage.clickOnLoginButton();
            log.info("login panel init");
            LoginPanel loginPanel = loginPage.getLoginPanel();
            log.info("registration page init");
            RegistrationPage registrationPage = loginPanel.clickOnRegistrationButton();
            log.info("registration panel panel init");
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
            driverManager.quitDriver();
        }
    }

    @Test
    public void passwordChangeTest() {
        log.info("home page init");
        HomePage homePage = goToLink();
        log.info("login page init");
        LoginPage loginPage = homePage.clickOnLoginButton();
        log.info("login panel init");
        LoginPanel loginPanel = loginPage.getLoginPanel();
        log.info("email send");
        loginPanel.sendKeysToEmailField(emailList.get(0));
        log.info("pass send");
        loginPanel.sendKeysToPasswordField(passwordList.get(0));
        log.info("click on sign in button");
        loginPanel.clickOnSignInButton();
        log.info(emailList.get(0));
        log.info(passwordList.get(0));
        log.info("user page init");
        UserPage userPage = homePage.clickOnUserButton();
        log.info("user information page init");
        UserInformationPage userInformationPage = userPage.clickOnUserInformationButton();
        log.info("generate new pass");
        String newPass = PasswordGenerator.generateRandomPassword(10);
        log.info("change pass");
        userInformationPage.changePassword(passwordList.get(0), newPass);
        userInformationPage.clickSaveButton();
        userInformationPage.clickOnLogOutButton();
        loginPanel = loginPage.getLoginPanel();
        loginPanel.sendKeysToEmailField(emailList.get(0));
        loginPanel.sendKeysToPasswordField(passwordList.get(0));
        loginPanel.clickOnSignInButton();
        loginPanel = loginPage.getLoginPanel();
        log.info("first check with invalid password passed");
        Assert.assertEquals(loginPanel.getMassageFromError(), "Ошибка авторизации.");
        loginPanel.sendKeysToPasswordField(newPass);
        loginPanel.clickOnSignInButton();
        log.info("second check with valid password passed");
        Assert.assertEquals(userInformationPage.getTextFromHeader(), "Ваша персональная информация");
    }

    @AfterTest
    public void cleanLists() {
        emailList.clear();
        passwordList.clear();
    }

    @AfterMethod
    public void tearDown() {
        log.info("InTestAfteBeforeQIUT");
        driver.quit();
    }

}
