package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnection;

import model.User;

public class BankController {

    public static Double checkBalance(User user) {
        String sql = """
                         SELECT balance FROM users WHERE id = ?
                         """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                System.out.println("Something went wrong.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void withdraw(User user, Double amount) {

        Double currentBalance = checkBalance(user);

        if (currentBalance == null) {
            System.out.println("Cannot retrieve balance.");
            return;
        }

        if (currentBalance < amount) {
            System.out.println("Insufficient amount to withdraw.");
            return;
        }

        String sql = """
        UPDATE users 
        SET balance = balance - ? 
        WHERE id = ?
    """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, user.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                Double newBalance = checkBalance(user);
                user.setBalance(newBalance);
                System.out.println("You withdrew: " + amount + ". New balance: " + newBalance);
            } else {
                System.out.println("Something went wrong with the withdrawal.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deposit(User user, Double amount) {
        if (amount <= 0) {
            System.out.println("Cannot deposit 0 or below.");
        }

        String sql = """
                     UPDATE users
                     Set balance = balance + ?
                     WHERE id = ?
                     """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setDouble(2, user.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                Double newBalance = checkBalance(user);
                user.setBalance(newBalance);
                System.out.println("Deposit successfully. Your new balance: " + newBalance);
            } else {
                System.out.println("Something went wrong.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void changePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        if (!user.verifyPassword(oldPassword)) {
            System.out.println("Old password does no match");
            return;
        }
        
        if (newPassword.isBlank() || !newPassword.equals(confirmPassword)) {
            System.out.println("Password and confirm password does not match.");
            return;
        }
        
        String sql = """
                     UPDATE users
                     Set password = ?
                     where id = ?
                     """;
        
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newPassword);
            ps.setInt(2, user.getId());
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Password successfully changed.");
                user.setPassword(newPassword);
            } else {
                System.out.println("Something went wrong.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
