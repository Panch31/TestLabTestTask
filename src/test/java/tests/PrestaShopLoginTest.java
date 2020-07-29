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
import pages.page.*;
import util.DriverManager;
import util.DriverManagerFactory;
import util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

public class PrestaShopLoginTest extends TestBase {

    List<String> passwordList = new ArrayList<>();
    List<String> emailList = new ArrayList<>();

    @BeforeTest
    public void registrationAsUser() {
        try {
            DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
            WebDriver driver = driverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            log.info("driver beforeMethodStarted started");
            String pass = PasswordGenerator.generateRandomPassword(10);
            HomePage homePage = new HomePage(driver);
            driver.get(Url.HOMEPAGE.getLink());
            homePage.clickOnLoginButton();
            LoginPage loginPage = new LoginPage(driver);
            RegistrationPage registrationPage = loginPage.clickOnRegistrationButton(driver);
            registrationPage.sendKeysToFirstNameField("Yevhenii");
            log.info("Yevhenii");
            registrationPage.sendKeysToLastNameField("Panchenko");
            log.info("Panchenko");
            registrationPage.sendKeysToPasswordField(pass);
            log.info("pass send");
            String email = registrationPage.sendKeysToEmailField();
            log.info("Yemail send");
            passwordList.add(pass);
            log.info("pass put to list");
            emailList.add(email);
            log.info("email put to list");
            registrationPage.clickOnSaveButton();
            log.info("save");
            log.info("driver out");
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    @Test
    public void passwordChangeTest(){
        HomePage homePage = goToLink();
        homePage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendKeysToEmailField(emailList.get(0));
        loginPage.sendKeysToPasswordField(passwordList.get(0));
        log.info("click on sign in button");
        loginPage.clickOnSignInButton();
        log.info(emailList.get(0));
        log.info(passwordList.get(0));
//        UserPage userPage = homePage.clickOnUserButton();
        UserPage userPage = new UserPage(driver);
        UserInformationPage userInformationPage = new UserInformationPage(driver);
        userPage.clickOnUserInformationButton();
        String newPass = PasswordGenerator.generateRandomPassword(10);
        userInformationPage.changePassword(passwordList.get(0),newPass);
        userInformationPage.clickSaveButton();
        userInformationPage.clickOnLogOutButton();
        loginPage.sendKeysToEmailField(emailList.get(0));
        loginPage.sendKeysToPasswordField(passwordList.get(0));
        loginPage.clickOnSignInButton();
        Assert.assertEquals(loginPage.getMassageFromError(),"Ошибка авторизации.");
        loginPage.sendKeysToPasswordField(newPass);
        loginPage.clickOnSignInButton();
        Assert.assertEquals(userInformationPage.getTextFromHeader(),"Ваша персональная информация");

    }

    @AfterTest
    public void cleanLists(){
        emailList.clear();
        passwordList.clear();
    }

}
