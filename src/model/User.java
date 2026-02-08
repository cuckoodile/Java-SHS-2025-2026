package model;


public class User {
    private int id;
    private String username;
    private String password;
    private Double balance;
    
    public User(int id, String username, String password, Double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setBalance(Double amount) {
        this.balance = amount;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}