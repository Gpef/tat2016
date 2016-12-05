package autotests.utils.config;

import autotests.utils.io.PropertiesReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Stores all app properties.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.11.2016
 */
public class Config {
    private static final String DRIVERS_PATH = "\\src\\main\\resources\\drivers";
    private static final String CONF_PATH = "\\src\\main\\resources";
    private static final String CONF_FILE = "test.properties";

    private static final Config instance = new Config();

    private final String wpHost;
    private final String wpPort;
    private final HashMap<Object, Object> wpUsers;

    private final String dbDatabase;
    private final String dbUser;
    private final String dbPassword;
    private final String dbUrl;
    private final String dbDriverClassName;


    public static Config getConfig() {
        return instance;
    }

    private Config() {
        Properties properties = PropertiesReader.getProperties("test.properties");
        wpHost = "http://" + properties.getProperty("wpat.host", "localhost");
        wpPort = properties.getProperty("wpat.port", "8888");

        dbDatabase = properties.getProperty("wpat.db.name", "wordpress");
        dbUser = properties.getProperty("wpat.db.user", "wordpress");
        dbPassword = properties.getProperty("wpat.db.password", "root");
        dbUrl = properties.getProperty("wpat.db.url", "jdbc:mysql://localhost:8889");
        dbDriverClassName = properties.getProperty("wpat.db.url.driver", "org.mysql.jdbc");

        HashMap<Object, Object> users = new HashMap<>();
        Properties userProperties = PropertiesReader.cutPropertiesForPrefix(
                "test.properties",
                "wpat.user"
        );
        for (Map.Entry<Object, Object> entry : userProperties.entrySet()) {
            users.put(entry.getKey().toString(), entry.getValue().toString());
        }
        wpUsers = users;

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
        System.setProperty("webdriver.base.url", wpHost);
    }

    public String getHostAddress() {
        return "http://" + wpHost + ":" + wpPort + "/";
    }

    public String getWpHost() {
        return wpHost;
    }

    public String getWpPort() {
        return wpPort;
    }

    public HashMap<Object, Object> getWpUsers() {
        return wpUsers;
    }

    public String getDbDatabase() {
        return dbDatabase;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }
}
