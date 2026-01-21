/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app1;

import java.util.Scanner;

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
        > Scanners

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
//        Phone myPhone = new Phone("IPhone", "De guzman", 10000.00);
//        Phone yourPhone = new Phone("Samsung", "Gutierrez", 12000.00);
        
//        myPhone.brand = "Oppo";
        
//        System.out.println(myPhone.getBrand());
//        myPhone.setBrand("Hwawei");
//        System.out.println(myPhone.getBrand());
//        System.out.println(myPhone.owner);
//        System.out.println(myPhone.price);
//        
//        System.out.println(yourPhone.getBrand());
//        System.out.println(yourPhone.owner);
//        System.out.println(yourPhone.price);
        
//        myPhone.openPhone();
        
        /* 
            Scanner methods:
                - next(): Returns the next token as a String. 
                - nextLine(): Advances the scanner past the current line and returns the input that was skipped (useful for reading entire lines, including spaces). 
                - nextInt(): Returns the next token as an int. 
                - nextDouble(): Returns the next token as a double. 
                - nextBoolean(): Returns the next token as a boolean. 
                - nextFloat(), nextLong(), nextShort(), nextByte(): Return the next token as their respective primitive types. 
        */

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter phone brand: ");
        String brand = sc.nextLine();
        System.out.println("Enter phone owner: ");
        String owner = sc.nextLine();
        System.out.println("Enter phone price: ");
        Double price = sc.nextDouble();
        
        Phone myPhone = new Phone(brand, owner, price);
        
        System.out.println(myPhone.getBrand());
        System.out.println(myPhone.owner);
        System.out.println(myPhone.price);
    }
}



/*
    ACTIVITY 3
        Create a class named "Bank", the class will have private attributes: accountName, accountPassword, and accountBalance.
        The bank class will have functions as described below.
        Each user will be an instance of the class, which value will be a user input using Scanner.

    class Bank
        Attributes
                accName = str
                accPass = str
                balance = int

        Functions
                checkBalance -> int/str
                        - check first for the user name and password
                deposit -> str/void
                        - ask for deposit amount
                withdraw -> str/void
                        - check first for the user name and password before asking for withdrawal amount
                        - withdrawal amount cant be greater than the available balance
                changePass -> str/void
                        - ask for the current password first before asking for a new one


*/





