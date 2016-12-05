package autotests.utils.database;

import autotests.utils.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Performs connection to database.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.11.2016
 */
public class DBConnector {
    private Config config = Config.getConfig();

    /**
     * Opens connection with database
     *
     * @return opened connection
     */
    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    config.getDbUrl(),
                    config.getDbUser(),
                    config.getDbPassword()
            );
        } catch (SQLException e) {
            // Handle SQLException if such has been thrown
        }
        return connection;
    }

    /**
     * Closes opened connection
     * @param connection connection to close
     */
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle SQLException if such has been thrown
        }
    }
}
