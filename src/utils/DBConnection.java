package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/inventory";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public static void initializeDatabase() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS users(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     username VARCHAR(50) NOT NULL UNIQUE,
                     password VARCHAR(100) NOT NULL,
                     balance DECIMAL(10,2) DEFAULT 0.00
                     )
                     """;
        
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
                ps.execute();
                
                System.out.println("Database created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
