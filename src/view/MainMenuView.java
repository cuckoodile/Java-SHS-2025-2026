package view;

import controller.AuthController;
import controller.BankController;

import java.util.Scanner;

public class MainMenuView {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthController auth;
    private final BankController bank;
    private final LoginView loginView;
    private final RegisterView registerView;
    private final UserMenuView userMenuView;

    public MainMenuView(AuthController auth, BankController bank) {
        this.auth = auth;
        this.bank = bank;
        this.loginView = new LoginView(auth, bank, this::showMainMenu);
        this.registerView = new RegisterView(auth, bank, this::showMainMenu);
        this.userMenuView = new UserMenuView(bank, this::showMainMenu);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== Simple Bank ===");
            if (bank.getCurrentUser() != null) {
                userMenuView.showUserMenu();
                continue;
            }

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    loginView.showLogin();
                    break;
                case "2":
                    registerView.showRegister();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}