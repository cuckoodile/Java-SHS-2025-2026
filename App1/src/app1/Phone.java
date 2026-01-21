package app1;

public class Phone {
//    Attributs/ Properties
    private String brand = "Infinix";
    String owner = "Ian";
    Double price = 10000.00;
    
//    Class Constructor
    public Phone(String newBrand, String newOwner, Double newPrice) {
        this.brand = newBrand;
        this.owner = newOwner;
        this.price = newPrice;
    }
    
//    Class method or class action
    void openPhone() {
        System.out.println(this.brand + " is open!");
    }
    
//    GETTERS AND SETTERS
//    Getters: Function that gets the private attribute then return it
    public String getBrand() {
        return this.brand;
    }
    
//    Setters: Function that receives a value then set it to the private attribute
    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }
}
