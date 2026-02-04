package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    // ────────────────────────────────────────────────
    // Switch to Laragon / MariaDB (MySQL compatible)
    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "";  // blank in fresh Laragon

    // If you created a limited user earlier:
    // private static final String USER = "devuser";
    // private static final String PASSWORD = "yourpass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        // MySQL/MariaDB syntax — fully supported
        // DOWNLOAD MYSQL jar --> https://dev.mysql.com/downloads/connector/j/

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS users (
                id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                username  VARCHAR(50) NOT NULL UNIQUE,
                password  VARCHAR(50) NOT NULL UNIQUE,
                is_active INT NOT NULL DEFAULT 1
            )
            """);
            System.out.println("Table 'users' created or already exists.");

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS productType (
                id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                name      VARCHAR(50) NOT NULL UNIQUE
            )
            """);
            System.out.println("Table 'product_type' created or already exists.");

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS products (
                id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                name      VARCHAR(50) NOT NULL UNIQUE,
                type      INT NOT NULL,
                price     DECIMAL,
                stock     INT DEFAULT 0,
                type_id   BIGINT,
                               
                FOREIGN KEY(type_id) REFERENCES productType(id)
            )
            """);
            System.out.println("Table 'products' created or already exists.");
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}