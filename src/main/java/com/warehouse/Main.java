// File: Main.java
package com.warehouse;

import com.warehouse.components.NavigationBar;
import com.warehouse.db.DatabaseConnection;
import com.warehouse.views.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame frame;
    private static JPanel mainPanel;

    public static void main(String[] args) {
        // Initialize the database connection
        DatabaseConnection.getConnection();

        // Create main frame
        frame = new JFrame("Warehouse Management Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Top panel - Navigation Bar
        NavigationBar navigationBar = new NavigationBar();
        frame.add(navigationBar, BorderLayout.NORTH);

        // Main panel - Center
        mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Add all panels to the main panel
        mainPanel.add(new DashboardPanel(), "DashboardPanel");
        mainPanel.add(new InventoryPanel(), "InventoryPanel");
        mainPanel.add(new OrderPanel(), "OrderPanel");
        mainPanel.add(new ExpensePanel(), "ExpensePanel");
        mainPanel.add(new SalesReportPanel(), "SalesReportPanel");
        mainPanel.add(new LowStockReportPanel(), "LowStockReportPanel");
        mainPanel.add(new StockValuePanel(), "StockValuePanel");
        mainPanel.add(new RevenuePanel(), "RevenuePanel");
        mainPanel.add(new InvoicePanel(), "InvoicePanel");
        mainPanel.add(new UserPanel(), "UserPanel");
        //mainPanel.add(new AccountingPanel(), "AccountingPanel");

        // Show the DashboardPanel by default
        switchPanel("DashboardPanel");

        // Set frame visibility
        frame.setVisible(true);

        // Add a shutdown hook to close the database connection when the application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DatabaseConnection.closeConnection();
            System.out.println("Database connection closed.");
        }));
    }

    // Method to switch panels based on user selection
    public static void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }
}
