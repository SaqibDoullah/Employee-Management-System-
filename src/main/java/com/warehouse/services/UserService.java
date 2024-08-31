// File: UserService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.User;
import com.warehouse.models.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserService {

    public void addUser(User user) {
        String query = "INSERT INTO Users (userId, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole().name());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully: " + user.getUsername());
            } else {
                System.out.println("User addition failed: " + user.getUsername());
            }
        } catch (SQLException e) {
            System.out.println("SQLException during user addition: " + e.getMessage());
        }
    }

    public User authenticateUser(String username, String password) {
        User user = null;
        Connection conn = DatabaseConnection.getConnection(); // Ensure connection is open
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role"))
                );
                System.out.println("Authentication successful for user: " + username);
            } else {
                System.out.println("Authentication failed for user: " + username);
            }

        } catch (SQLException e) {
            System.out.println("SQLException during authentication: " + e.getMessage());
        }
        return user;
    }
    

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role"))
                );
                users.add(user);
                System.out.println("Loaded user: " + user.getUsername());
            }
        } catch (SQLException e) {
            System.out.println("SQLException during loading users: " + e.getMessage());
        }

        return users;
    }
}
