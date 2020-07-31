package pages.page;

import org.openqa.selenium.WebDriver;
import pages.panel.LoginPanel;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPanel getLoginPanel() {
        return new LoginPanel(driver);
    }
}
