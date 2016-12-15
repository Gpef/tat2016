package autotests.utils.database;

import autotests.utils.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Performs operations with database.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.12.2016
 */
public class DatabaseWorker {
    public static class Role {
        private final String meta_capabilities;
        private final int level;

        public Role(String metaCapabilities, int level) {
            this.meta_capabilities = metaCapabilities;
            this.level = level;
        }

        public String getMetaCapabilities() {
            return meta_capabilities;
        }

        public int getLevel() {
            return level;
        }
    }

    public static final Role ADMINISTRATOR = new Role("a:1:{s:13:\"administrator\";b:1;}", 10);
    public static final Role EDITOR = new Role("a:1:{s:6:\"editor\";b:1;}", 7);
    public static final Role AUTHOR = new Role("a:1:{s:6:\"author\";b:1;}", 2);
    public static final Role CONTRIBUTOR = new Role("a:1:{s:11:\"contributor\";b:1;}", 1);
    public static final Role SUBSCRIBER = new Role("a:1:{s:10:\"subscriber\";b:1;}", 0);

    private static final String USER_ID = "ID";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASS = "user_pass";

    private static final String USERMETA_META_KEY_CAPABILITIES = "wp_capabilities";
    private static final String USERMETA_META_KEY_USER_LEVEL = "wp_user_level";

    private static final String SQL_SELECT_USER_ID_BY_LOGIN = "SELECT ID FROM wordpress.wp_users WHERE user_login = ?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO wordpress.wp_users (user_login, user_pass, user_nicename, user_email, " +
                    "user_registered, user_status, display_name) " +
                    "VALUES(?, MD5(?), ?, ?,'2011-06-07 00:00:00','0',?);";

    private static final String SQL_INSERT_USERMETA =
            "INSERT INTO wordpress.wp_usermeta (user_id, meta_key, meta_value) " +
                    "VALUES(?, ?, ?);";

    private static final String SQL_DELETE_USERMETA = "DELETE FROM wordpress.wp_usermeta WHERE user_id = ?";

    private static final String SQL_DELETE_USER = "DELETE FROM wordpress.wp_users WHERE user_login = ?";

    private DatabaseConnector connector = new DatabaseConnector();

    /**
     * Adds user to database. Creates record in wp_users tables and wp_usermeta table.
     *
     * @param login    new user login
     * @param password new user password (will be encrypted with MD5)
     * @param role     new user role(such as Admin, Editor etc.)
     * @throws DatabaseException if error ocurred while creating new record
     */
    public void addUser(String login, String password, Role role) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.openConnection();

            // Adding record to wp_users
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER, new String[]{USER_ID});
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, login + "@mail.test");
            preparedStatement.setString(5, login);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long userId=resultSet.getLong(1);

            // Adding record to wp_usermeta with capabilities
            preparedStatement = connection.prepareStatement(SQL_INSERT_USERMETA);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, USERMETA_META_KEY_CAPABILITIES);
            preparedStatement.setString(3, role.getMetaCapabilities());
            preparedStatement.executeUpdate();

            // Adding record to wp_usermeta with user_level
            preparedStatement = connection.prepareStatement(SQL_INSERT_USERMETA);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, USERMETA_META_KEY_USER_LEVEL);
            preparedStatement.setInt(3, role.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseResourcesManager.closeJdbcResources(preparedStatement, resultSet);
            connector.closeConnection(connection);
        }
    }

    public void deleteUser(String login) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.openConnection();

            // Deleting record to wp_users
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_ID_BY_LOGIN, new String[]{USER_ID});
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long userId = resultSet.getLong(1);

            // Deleting record to wp_usermeta with capabilities
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            // Deleting record to wp_usermeta with user_level
            preparedStatement = connection.prepareStatement(SQL_DELETE_USERMETA);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseResourcesManager.closeJdbcResources(preparedStatement, null);
            connector.closeConnection(connection);
        }
    }
}
