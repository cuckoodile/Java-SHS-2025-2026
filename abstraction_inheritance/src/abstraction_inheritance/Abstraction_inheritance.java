/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package abstraction_inheritance;

/**
 *
 * @author Admin
 */
public class Abstraction_inheritance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Pokemon pikachu = new Pikachu(10);
        Pokemon charmander = new Charmander(8);

        pikachu.showInfo();
        pikachu.attack();

        System.out.println();

        charmander.showInfo();
        charmander.attack();
    }
}
