package controller;

import utils.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankController {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        saveLoggedUser(user != null ? user.getId() : null);
    }

    private void saveLoggedUser(Integer userId) {
        String sql = "UPDATE BANK SET logged_user = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (userId == null) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, userId);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving logged user: " + e.getMessage());
        }
    }

    public void loadLoggedUser() {
        String sql = "SELECT u.* FROM BANK b LEFT JOIN USERS u ON b.logged_user = u.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next() && rs.getString("username") != null) {
                currentUser = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("balance")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error loading logged user: " + e.getMessage());
        }
    }

    public int getBalance() {
        if (currentUser == null) return 0;
        // Re-fetch from DB to be sure
        String sql = "SELECT balance FROM USERS WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, currentUser.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    currentUser.setBalance(rs.getInt("balance"));
                    return rs.getInt("balance");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching balance: " + e.getMessage());
        }
        return currentUser.getBalance();
    }

    public boolean deposit(int amount) {
        if (amount <= 0) return false;
        String sql = "UPDATE USERS SET balance = balance + ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setInt(2, currentUser.getId());
            boolean success = ps.executeUpdate() > 0;
            if (success) currentUser.setBalance(currentUser.getBalance() + amount);
            return success;
        } catch (SQLException e) {
            System.out.println("Deposit error: " + e.getMessage());
            return false;
        }
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) return false;
        int current = getBalance();
        if (amount > current) return false;

        String sql = "UPDATE USERS SET balance = balance - ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setInt(2, currentUser.getId());
            boolean success = ps.executeUpdate() > 0;
            if (success) currentUser.setBalance(current - amount);
            return success;
        } catch (SQLException e) {
            System.out.println("Withdraw error: " + e.getMessage());
            return false;
        }
    }
}