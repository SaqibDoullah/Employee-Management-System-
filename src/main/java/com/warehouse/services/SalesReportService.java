// File: SalesReportService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.SalesReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalesReportService {

    public SalesReport generateSalesReport(String startDate, String endDate) {
        int totalOrders = 0;
        double totalRevenue = 0.0;

        String query = "SELECT COUNT(orderId) AS orderCount, SUM(quantity * itemPrice) AS revenue " +
                       "FROM Orders JOIN Inventory ON Orders.itemId = Inventory.itemId " +
                       "WHERE orderDate BETWEEN '" + startDate + "' AND '" + endDate + "'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalOrders = rs.getInt("orderCount");
                totalRevenue = rs.getDouble("revenue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SalesReport(startDate + " to " + endDate, totalOrders, totalRevenue);
    }
}
