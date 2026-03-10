package utils;

/**
 *
 * @author lhourde
 */

import java.sql.*;


public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/vendingmachine";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static void initializeDatabase() {
        String users = """
                       CREATE TABLE IF NOT EXISTS users (
                       id int PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL
                       )
                       """;
        
        String vMachine = """
                          CREATE TABLE IF NOT EXISTS vmachine (
                          id int PRIMARY KEY AUTO_INCREMENT,
                          money DOUBLE(10,2) NOT NULL DEFAULT 0.00
                          )
                          """;
        
        String products = """
                          CREATE TABLE IF NOT EXISTS products (
                          id int PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL UNIQUE,
                          price DOUBLE(10,2) NOT NULL,
                          stock int NOT NULL DEFAULT 0
                          )
                          """;
        
        try (Connection con = getConnection(); 
            PreparedStatement pUsers = con.prepareStatement(users);
            PreparedStatement pVMachine = con.prepareStatement(vMachine);
            PreparedStatement pProducts = con.prepareStatement(products);) {
            
            System.out.println("Initializing database...");
            
            pUsers.execute();
            pVMachine.execute();
            pProducts.execute();
            
            System.out.println("Database Initialized complete.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
