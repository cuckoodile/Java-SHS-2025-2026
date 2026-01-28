/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstraction_inheritance;

/**
 *
 * @author Admin
 */
public class Charmander extends Pokemon {

    public Charmander(int level) {
        super("Charmander", level);
    }

    @Override
    public void attack() {
        System.out.println("Charmander uses Flamethrower!");
    }
}
