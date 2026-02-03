package database;

import controller.AuthController;
import controller.BankController;
import utils.DBConnection;
import view.MainMenuView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Ensure database is initialized
            DBConnection.getConnection();

            AuthController auth = new AuthController();
            BankController bank = new BankController();

            // Load previously logged user (if any)
            bank.loadLoggedUser();

            MainMenuView mainMenu = new MainMenuView(auth, bank);
            mainMenu.showMainMenu();

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            DBConnection.closeConnection();
        }
    }
}