package view;

import controller.BankController;
import controller.AuthController;

import java.util.Scanner;

public class UserMenuView {

    private final Scanner scanner = new Scanner(System.in);
    private final BankController bank;
    private final Runnable logoutAction;

    public UserMenuView(BankController bank, Runnable logoutAction) {
        this.bank = bank;
        this.logoutAction = logoutAction;
    }

    public void showUserMenu() {
        while (true) {
            String name = bank.getCurrentUser().getName();
            System.out.printf("\nWelcome, %s!\n", name);
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change Password");
            System.out.println("5. Log Out");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Current balance: ₱" + bank.getBalance());
                    break;

                case "2":
                    System.out.print("Enter amount to deposit: ");
                    try {
                        int amount = Integer.parseInt(scanner.nextLine().trim());
                        if (bank.deposit(amount)) {
                            System.out.println("Deposit successful!");
                        } else {
                            System.out.println("Invalid amount.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        int amount = Integer.parseInt(scanner.nextLine().trim());
                        if (bank.withdraw(amount)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient balance or invalid amount.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "4":
                    System.out.print("New password: ");
                    String newPass = scanner.nextLine().trim();
                    System.out.print("Confirm new password: ");
                    String confirm = scanner.nextLine().trim();

                    if (newPass.equals(confirm) && !newPass.isEmpty()) {
                        if (new AuthController().updatePassword(bank.getCurrentUser().getId(), newPass)) {
                            System.out.println("Password changed successfully.");
                        }
                    } else {
                        System.out.println("Passwords do not match or empty.");
                    }
                    break;

                case "5":
                    bank.setCurrentUser(null);
                    System.out.println("Logged out.");
                    logoutAction.run();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}