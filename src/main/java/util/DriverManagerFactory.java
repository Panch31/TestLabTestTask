package util;

public class DriverManagerFactory {


    public static DriverManager getManager(DriverType type) {


        System.out.println("0");
        DriverManager driverManager = null;


        switch (type) {
            case CHROME:
                System.out.println("1");
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                //  driverManager = new FirefoxDriverManager();
                break;
            default:
                //   driverManager = new SafariDriverManager();
                break;
        }
        System.out.println("2");
        return driverManager;


    }
}
