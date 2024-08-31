package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.InventoryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private Connection connection;

    public InventoryService() {
        // Use a single connection for this service instance
        this.connection = DatabaseConnection.getConnection();

        // Create inventory table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Inventory ("
                + "itemId TEXT PRIMARY KEY, "
                + "itemName TEXT, "
                + "quantity INTEGER, "
                + "location TEXT, "
                + "reorderLevel INTEGER"
                + ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem(InventoryItem item) {
        String query = "INSERT INTO Inventory (itemId, itemName, quantity, location, reorderLevel) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, item.getItemId());
            pstmt.setString(2, item.getItemName());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setString(4, item.getLocation());
            pstmt.setInt(5, item.getReorderLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        String query = "SELECT * FROM Inventory";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                items.add(new InventoryItem(
                        rs.getString("itemId"),
                        rs.getString("itemName"),
                        rs.getInt("quantity"),
                        rs.getString("location"),
                        rs.getInt("reorderLevel")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public InventoryItem getItemById(String itemId) {
        String query = "SELECT * FROM Inventory WHERE itemId = ?";
        InventoryItem item = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                item = new InventoryItem(
                        rs.getString("itemId"),
                        rs.getString("itemName"),
                        rs.getInt("quantity"),
                        rs.getString("location"),
                        rs.getInt("reorderLevel")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    public void updateItem(String itemId, InventoryItem updatedItem) {
        String query = "UPDATE Inventory SET itemName = ?, quantity = ?, location = ?, reorderLevel = ? WHERE itemId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, updatedItem.getItemName());
            pstmt.setInt(2, updatedItem.getQuantity());
            pstmt.setString(3, updatedItem.getLocation());
            pstmt.setInt(4, updatedItem.getReorderLevel());
            pstmt.setString(5, updatedItem.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteItem(String itemId) {
        String query = "DELETE FROM Inventory WHERE itemId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, itemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeService() {
        DatabaseConnection.closeConnection();
    }
}
