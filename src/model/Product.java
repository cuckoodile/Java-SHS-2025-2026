package model;

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
}
