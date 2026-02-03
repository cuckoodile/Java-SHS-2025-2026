package view;

import controller.AuthController;
import controller.BankController;

import java.util.Scanner;

public class RegisterView {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthController auth;
    private final BankController bank;
    private final Runnable returnToMain;

    public RegisterView(AuthController auth, BankController bank, Runnable returnToMain) {
        this.auth = auth;
        this.bank = bank;
        this.returnToMain = returnToMain;
    }

    public void showRegister() {
        System.out.println("\n--- Register ---");
        System.out.print("Full name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Confirm password: ");
        String confirm = scanner.nextLine().trim();

        if (!password.equals(confirm)) {
            System.out.println("Passwords do not match!");
            returnToMain.run();
            return;
        }

        if (auth.register(name, username, password)) {
            System.out.println("Registration successful!");
            // Auto login
            bank.setCurrentUser(auth.login(username, password));
        }

        returnToMain.run();
    }
}