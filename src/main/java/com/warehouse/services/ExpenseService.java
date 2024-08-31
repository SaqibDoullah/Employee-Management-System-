// File: ExpenseService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    public ExpenseService() {
        // Create expense table if it doesn't exist
        try (Connection conn = DatabaseConnection.getConnection()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Expenses ("
                    + "expenseId TEXT PRIMARY KEY, "
                    + "description TEXT, "
                    + "amount REAL, "
                    + "date TEXT"
                    + ");";
            Statement stmt = conn.createStatement();
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addExpense(Expense expense) {
        String query = "INSERT INTO Expenses (expenseId, description, amount, date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, expense.getExpenseId());
            pstmt.setString(2, expense.getDescription());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setString(4, expense.getDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM Expenses";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                expenses.add(new Expense(
                        rs.getString("expenseId"),
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        rs.getString("date")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expenses;
    }

    public double calculateTotalExpenses() {
        double totalExpenses = 0.0;
        String query = "SELECT SUM(amount) AS totalExpenses FROM Expenses";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalExpenses = rs.getDouble("totalExpenses");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalExpenses;
    }

    public void deleteExpense(String expenseId) {
        String query = "DELETE FROM Expenses WHERE expenseId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, expenseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
