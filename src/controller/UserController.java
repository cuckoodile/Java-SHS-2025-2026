package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.DBConnection;

public class UserController {

    public User login(String username, String password) {
        if (password.isBlank() || username.isBlank()) {
            System.out.println("\nUsername or Password is blank!");
            return null;
        }

        String sql = """
                     SELECT * FROM users WHERE username = ? AND password = ?
                     """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nLogged in as " + rs.getString("username"));
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
            }

            System.out.println("\nIncorrect Username or Password.");
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User register(String username, String password, String confirmPassword) {

        if (username.isBlank()) {
            System.out.println("\nUsername is empty.");
            return null;
        }

        if (password.isBlank() || !password.equals(confirmPassword)) {
            System.out.println("\nPassword and Confirm Password does not match.");
            return null;
        }

        String sql = """
        INSERT INTO users(username, password, balance)
        VALUES(?, ?, ?)
    """;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDouble(3, 0.00);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet keys = ps.getGeneratedKeys();

                if (keys.next()) {
                    int id = keys.getInt(1);

                    User user = new User(id, username, password, 0.00);

                    System.out.println("\n" + username + " is created successfully.");

                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
