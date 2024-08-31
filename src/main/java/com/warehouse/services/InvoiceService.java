// File: InvoiceService.java
package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.Invoice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvoiceService {

    public Invoice generateInvoice(String orderId) {
        Invoice invoice = null;

        String query = "SELECT Orders.orderId, Orders.customerName, Inventory.itemName, Orders.quantity, Inventory.itemPrice, Orders.orderDate " +
                       "FROM Orders JOIN Inventory ON Orders.itemId = Inventory.itemId " +
                       "WHERE Orders.orderId = '" + orderId + "'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String customerName = rs.getString("customerName");
                String itemName = rs.getString("itemName");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("itemPrice");
                String date = rs.getString("orderDate");
                double totalAmount = quantity * price;

                invoice = new Invoice(orderId, customerName, itemName, quantity, price, date, totalAmount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoice;
    }
}

