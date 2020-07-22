package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.BasePageObject;

public class MainSelectMenuPanel extends BasePageObject {

    private static By root = By.cssSelector("ul");

    public MainSelectMenuPanel(WebDriver driver) {
        super(driver, root);
    }

}