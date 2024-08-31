package com.warehouse.services;

import com.warehouse.db.DatabaseConnection;
import com.warehouse.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private Connection connection;

    public OrderService() {
        // Use a single connection for this service instance
        this.connection = DatabaseConnection.getConnection();

        // Create order table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Orders ("
                + "orderId TEXT PRIMARY KEY, "
                + "customerName TEXT, "
                + "itemId TEXT, "
                + "quantity INTEGER, "
                + "orderDate TEXT, "
                + "status TEXT"
                + ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO Orders (orderId, customerName, itemId, quantity, orderDate, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, order.getOrderId());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setString(3, order.getItemId());
            pstmt.setInt(4, order.getQuantity());
            pstmt.setString(5, order.getOrderDate());
            pstmt.setString(6, order.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getString("orderId"),
                        rs.getString("customerName"),
                        rs.getString("itemId"),
                        rs.getInt("quantity"),
                        rs.getString("orderDate"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public void updateOrder(Order order) {
        String query = "UPDATE Orders SET customerName = ?, itemId = ?, quantity = ?, orderDate = ?, status = ? WHERE orderId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, order.getCustomerName());
            pstmt.setString(2, order.getItemId());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setString(4, order.getOrderDate());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteOrder(String orderId) {
        String query = "DELETE FROM Orders WHERE orderId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // This can be called when you're done using the service, such as during application shutdown
    public void closeService() {
        DatabaseConnection.closeConnection();
    }
}
