/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstraction_inheritance;

/**
 *
 * @author Admin
 */
public class Pikachu extends Pokemon {

    public Pikachu(int level) {
        super("Pikachu", level);
    }

    @Override
    public void attack() {
        System.out.println("Pikachu uses Thunderbolt!");
    }
}
