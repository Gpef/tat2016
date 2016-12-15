package autotests.utils.database;

import autotests.utils.exceptions.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 12.12.2016
 */
public class DatabaseResourcesManager {

    /**
     * {@link java.sql.PreparedStatement} is a sub-interface of {@link Statement}
     * so we don't have to provide two separate methods to close them.
     *
     * @param statement {@link Statement} object
     * @param resultSet {@link ResultSet} object
     * @throws DatabaseException
     */
    public static void closeJdbcResources(Statement statement, ResultSet resultSet) throws DatabaseException {
        try {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
