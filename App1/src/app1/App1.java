/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app1;

/*
    - OOP   (Object Oriented Programming)
    - 4 Fundamentals (Pilars)
        > Encapsulation
            - Default       Package 
            - Public        Global
            - Private       Class (Same file only)
            - Protected     Class and Subclasses (Subclasses: Children)
        > Polymorphism
        > Inheritance
        > Abstraction
    - Class and Objects
        > Attributes (Object properties)
        > Methods (Class functions)
        > Class Constructor (Special function)
            - Parameters and Arguements
            - Make the class flexible or reusable
        > Getters and Setters

            Phone myPhone = new Phone();
            Phone myPhone2 = new Phone();
            Phone myPhone3 = new Phone();
               Phone is a class
               myPhone is an instance of Phone class

           LATER....
        Parent  (Protected attributes)
            - Child
                - Child (Grand Child)
 */

public class App1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      Class ObjName = new SameClass();
        Phone myPhone = new Phone("IPhone", "De guzman", 10000.00);
        Phone yourPhone = new Phone("Samsung", "Gutierrez", 12000.00);
        
//        myPhone.brand = "Oppo";
        
        System.out.println(myPhone.getBrand());
        myPhone.setBrand("Hwawei");
        System.out.println(myPhone.getBrand());
//        System.out.println(myPhone.owner);
//        System.out.println(myPhone.price);
//        
//        System.out.println(yourPhone.getBrand());
//        System.out.println(yourPhone.owner);
//        System.out.println(yourPhone.price);
        
//        myPhone.openPhone();
        
    }
}






