
import controller.BankController;
import controller.UserController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utils.DBConnection;
import view.UI;
import model.User;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static UserController uc = new UserController();

    public static void main(String[] args) {
        System.out.println("Project initialized.");

        DBConnection.initializeDatabase();

        while (true) {
            UI.showMainMenu();

            int choice = scan.nextInt();
            scan.nextLine();

            userCredential(choice);
        }

    }

    public static void userCredential(int choice) {
        switch (choice) {
            case 1 -> {
                System.out.println("\nLOGIN");

                System.out.println("Username: ");
                String username = scan.nextLine();

                System.out.println("Password: ");
                String password = scan.nextLine();

                User user = uc.login(username, password);

                if (user != null) {
                    userOption(user);
                }
            }

            case 2 -> {
                System.out.println("\nREGISTER");

                System.out.println("Username: ");
                String username = scan.nextLine();

                System.out.println("Password: ");
                String password = scan.nextLine();

                System.out.println("Confirm Password: ");
                String confirm_password = scan.nextLine();

                User user = uc.register(username, password, confirm_password);

                if (user != null) {
                    userOption(user);
                }
            }

            case 3 -> {
                System.out.println("\nThank you for using MFI bank.");
                System.exit(0);
            }
        }
    }

    public static void userOption(User user) {
        /*
            [1] Check Balance
            [2] Withdraw
            [3] Deposit
            [4] Change Password
            [5] Log out
        */
        
        boolean run = true;
        while(run) {
            UI.showUserMenu(user);
            
            int choice = scan.nextInt();
            scan.nextLine();
            
            switch(choice) {
                case 1 -> {
                    System.out.println("Remaining balance: " + BankController.checkBalance(user));
                }
                case 2 -> {
                    System.out.println("How much to withdraw? ");
                    Double amount = scan.nextDouble();
                    scan.nextLine();
                    
                    BankController.withdraw(user, amount);
                }
                case 3 -> {
                    System.out.println("How much to deposit? ");
                    Double amount = scan.nextDouble();
                    scan.nextLine();
                    
                    BankController.deposit(user, amount);
                }
                case 4 -> {
                    System.out.println("Enter old password: ");
                    String oldPassword = scan.nextLine();
                    
                    System.out.println("Enter new password: ");
                    String newPassword = scan.nextLine();
                    
                    System.out.println("Confirm password: ");
                    String confirmPassword = scan.nextLine();
                    
                    BankController.changePassword(user, oldPassword, newPassword, confirmPassword);
                }
                case 5 -> {
                    System.out.println("\nLogging out.");
                    run = false;
                }
            }
        }
    }
}
