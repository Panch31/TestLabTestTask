package pages.page;

import org.openqa.selenium.WebDriver;
import pages.panel.RegistrationPanel;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPanel getRegistrationPanel(){
        return new RegistrationPanel(driver);
    }

}
