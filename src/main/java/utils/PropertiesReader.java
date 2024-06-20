package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {

    private static Map<String, String> configMap = new HashMap<>();

    static {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
            properties.load(input);
            properties.forEach((key, value) -> configMap.put((String) key, (String) value));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getProperty(String key) {
        try {
            return configMap.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}
