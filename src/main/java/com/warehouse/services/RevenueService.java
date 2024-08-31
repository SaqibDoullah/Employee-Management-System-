// File: RevenueService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RevenueService {

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;

        String query = "SELECT SUM(quantity * itemPrice) AS totalRevenue " +
                       "FROM Orders JOIN Inventory ON Orders.itemId = Inventory.itemId " +
                       "WHERE status = 'Completed'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalRevenue = rs.getDouble("totalRevenue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRevenue;
    }
}
