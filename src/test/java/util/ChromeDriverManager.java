package util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class ChromeDriverManager extends DriverManager {


    private ChromeDriverService chService;
    String path;

    public String getDriverPath(){
        try{
            path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "chromedriver.exe";
        } catch (NullPointerException e){
            path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "chromedriver";
        }
        return path;
    }


    @Override
    public void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File (getDriverPath()))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }


    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--start-maximized");
        chromeOptions.merge(capabilities);
        driver = new ChromeDriver(chService, chromeOptions);
    }


}