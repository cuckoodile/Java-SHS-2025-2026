package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author lhourde
 */
public class Product {

    private int id;
    private String name;
    private Double price;
    private int stock;

    public Product(int id, String name, Double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    // Getters
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Double getPrice() {
        return this.price;
    }
    public int getStock() {
        return this.stock;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = """
                     SELECT * FROM products
                     """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

//                System.out.printf("Retrieved: ID %d Name %s Price %.2f Stock %d%n", id, name, price, stock);
                
                products.add(new Product(id, name, price, stock));
            }

            if (products.isEmpty()) {
                return null;
            } else {
                
                
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
