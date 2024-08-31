// File: InventoryReportService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.LowStockReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryReportService {

    public List<LowStockReport> generateLowStockReport() {
        List<LowStockReport> lowStockItems = new ArrayList<>();

        String query = "SELECT itemId, itemName, quantity, reorderLevel FROM Inventory WHERE quantity < reorderLevel";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lowStockItems.add(new LowStockReport(
                        rs.getString("itemId"),
                        rs.getString("itemName"),
                        rs.getInt("quantity"),
                        rs.getInt("reorderLevel")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lowStockItems;
    }

    public double calculateTotalStockValue() {
        double totalValue = 0.0;

        String query = "SELECT SUM(quantity * itemPrice) AS totalValue FROM Inventory";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalValue = rs.getDouble("totalValue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalValue;
    }
}
