package autotests.utils.config;

import autotests.utils.io.PropertiesReader;

import java.util.Map;
import java.util.Properties;

/**
 * Stores all app properties.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.11.2016
 */
public class WordpressConfig {
    private static final String CONF_FILE = "test.properties";

    private static final WordpressConfig instance = new WordpressConfig();

    private final String host;
    private final String adminLogin;
    private final String adminPassword;

    public static WordpressConfig getConfig() {
        return instance;
    }

    private WordpressConfig() {
        Properties properties = PropertiesReader.getProperties(CONF_FILE);
        host = properties.getProperty("wordpress.host", "http://localhost:8888");
        adminLogin = properties.getProperty("wordpress.admin.login", "admin");
        adminPassword = properties.getProperty("wordpress.admin.password", "admin");
        Properties drivers = PropertiesReader.getPropertiesForPrefix(CONF_FILE, "webdriver");
        for (Map.Entry<Object, Object> entry : drivers.entrySet()){
            if (System.getProperty((String)entry.getKey()) == null){
                System.setProperty((String)entry.getKey(), (String)entry.getValue());
            }
        }
        System.setProperty("webdriver.base.url", host);
    }

    public String getHost() {
        return host;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
