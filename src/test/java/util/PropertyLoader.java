package util;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String GENERAL_PROP_FILE = "/general.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(GENERAL_PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }
}
