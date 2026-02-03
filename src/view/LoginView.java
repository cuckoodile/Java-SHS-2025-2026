package view;

import controller.AuthController;
import controller.BankController;
import model.User;

import java.util.Scanner;

public class LoginView {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthController auth;
    private final BankController bank;
    private final Runnable returnToMain;

    public LoginView(AuthController auth, BankController bank, Runnable returnToMain) {
        this.auth = auth;
        this.bank = bank;
        this.returnToMain = returnToMain;
    }

    public void showLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        User user = auth.login(username, password);
        if (user != null) {
            bank.setCurrentUser(user);
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("Invalid username or password.");
        }
        returnToMain.run();
    }
}