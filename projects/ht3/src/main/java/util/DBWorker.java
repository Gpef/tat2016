package util;

import java.sql.*;

public class DBWorker {
    private Integer lastInsertId = 0;
    private static DBWorker instance = null;

    private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private final static String CONNECTION_URL = "jdbc:mysql://localhost/phonebook?user=root&password=oleg&useUnicode=true" +
            "&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";

    public static DBWorker getInstance() {
        if (instance == null) {
            instance = new DBWorker();
        }
        return instance;
    }

    private DBWorker() {
    }

    /**
     * Executing query to select data from database.
     *
     * @param query select query
     * @return set of data corresponds to select query
     */
    public ResultSet getDBData(String query) {
        Statement statement;
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("null on getDBData()!");
            return null;
        }
    }

    /**
     * Executing query to update data from database.
     *
     * @param query update query
     * @return number of updated rows
     */

    public Integer changeDBData(String query) {
        Statement statement;
        Connection connection;
        ResultSet resultSet;
        Integer affectedRows;
        try {
            connection = openConnection();
            Class.forName(DRIVER_CLASS_NAME);
            statement = connection.createStatement();
            affectedRows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                this.lastInsertId = resultSet.getInt(1);
            }
            return affectedRows;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("null on changeDBData()!");
        return 0;
    }

    private Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + ". Connection will be null");
        }
        return connection;
    }

    public Integer getLastInsertId() {
        return this.lastInsertId;
    }
}

