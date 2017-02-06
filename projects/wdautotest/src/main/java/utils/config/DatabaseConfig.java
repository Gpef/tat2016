package utils.config;

import utils.io.PropertiesReader;

import java.util.Properties;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 12.12.2016
 */
public class DatabaseConfig {
    private static final DatabaseConfig config = new DatabaseConfig();

    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    public static DatabaseConfig getConfig() {
        return config;
    }

    private DatabaseConfig() {
        Properties properties = PropertiesReader.getPropertiesForPrefix("test.properties", "database.");
        driverClassName = properties.getProperty("database.driverClassName");
        url = properties.getProperty("database.url");
        username = properties.getProperty("database.username");
        password = properties.getProperty("database.password");
        try {
            // Load the Database Driver Class
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            // Driver Class was not found
        }
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
