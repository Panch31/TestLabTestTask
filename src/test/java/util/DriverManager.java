package util;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager implements AutoCloseable{

    @Override
    public void close(){
        if (null != driver) {
            driver.quit();
            driver = null;
            System.out.println("Driver closed after error in Before Step");
        }
    }

    protected WebDriver driver;

    protected abstract void startService();

    protected abstract void stopService();

    protected abstract void createDriver();


    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }


    }


    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}
