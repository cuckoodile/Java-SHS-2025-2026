/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstraction_inheritance;

/**
 *
 * @author Admin
 */
public abstract class Pokemon {

    protected String name;
    protected int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // Abstract method (must be implemented by child classes)
    public abstract void attack();

    // Concrete method (shared behavior)
    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
    }
}
