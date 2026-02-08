package view;

import model.User;

public class UI {

    public static void showMainMenu() {
        System.out.println("""
               \nWelcome to MFI Bank!
               [1] Login
               [2] Register
               [3] Exit
               """);
    }

    public static void showUserMenu(User user) {
        System.out.println("\nHello " + user.getUsername() + "!");
        System.out.println("""
                            [1] Check Balance
                            [2] Withdraw
                            [3] Deposit
                            [4] Change Password
                            [5] Log out
                           """);
    }
}
