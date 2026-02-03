package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_URL = "jdbc:derby:testDB;create=true";
        

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                connection = DriverManager.getConnection(DB_URL);
                initializeDatabase();
            } catch (ClassNotFoundException e) {
                throw new SQLException("Derby driver not found", e);
            }
        }
        return connection;
    }

    private static void initializeDatabase() throws SQLException {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        
        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE USERS (" +
                "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "name VARCHAR(100) NOT NULL," +
                "username VARCHAR(50) UNIQUE NOT NULL," +
                "password VARCHAR(50) NOT NULL," +
                "balance INT DEFAULT 0" +
                ")"
            );

            stmt.executeUpdate(
                "CREATE TABLE BANK (" +
                "logged_user INT" +
                ")"
            );

            try (var rs = stmt.executeQuery("SELECT COUNT(*) FROM BANK")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    stmt.executeUpdate("INSERT INTO BANK (logged_user) VALUES (NULL)");
                }
            }

        } catch (SQLException e) {
            // Table already exists → ignore
            if (!e.getSQLState().equals("X0Y32") && !e.getSQLState().equals("X0Y55")) {
                throw e;
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                
            }
        }
    }
}