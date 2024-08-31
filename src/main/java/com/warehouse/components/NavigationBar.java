// File: NavigationBar.java (Optimized)
package com.warehouse.components;

import com.warehouse.views.*;
import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
    public NavigationBar() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JLabel("Warehouse Management Software"));

        JButton dashboardButton = new JButton("Dashboard");
        JButton inventoryButton = new JButton("Inventory");
        JButton orderButton = new JButton("Orders");
        JButton reportButton = new JButton("Sales Report");
        JButton lowStockReportButton = new JButton("Low Stock Report");
        JButton stockValueButton = new JButton("Stock Value Report");
        JButton expenseButton = new JButton("Manage Expenses");
        JButton revenueButton = new JButton("Revenue Tracking");
        JButton invoiceButton = new JButton("Invoice Generation");

        this.add(dashboardButton);
        this.add(inventoryButton);
        this.add(orderButton);
        this.add(reportButton);
        this.add(lowStockReportButton);
        this.add(stockValueButton);
        this.add(expenseButton);
        this.add(revenueButton);
        this.add(invoiceButton);
        // Additional buttons
        this.add(new JButton("Users"));

        // Button actions to switch panels
        dashboardButton.addActionListener(e -> switchToPanel(new DashboardPanel()));
        inventoryButton.addActionListener(e -> switchToPanel(new InventoryPanel()));
        orderButton.addActionListener(e -> switchToPanel(new OrderPanel()));
        reportButton.addActionListener(e -> switchToPanel(new SalesReportPanel()));
        lowStockReportButton.addActionListener(e -> switchToPanel(new LowStockReportPanel()));
        stockValueButton.addActionListener(e -> switchToPanel(new StockValuePanel()));
        expenseButton.addActionListener(e -> switchToPanel(new ExpensePanel()));
        revenueButton.addActionListener(e -> switchToPanel(new RevenuePanel()));
        invoiceButton.addActionListener(e -> switchToPanel(new InvoicePanel()));
    }

    private void switchToPanel(JPanel panel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.add(new NavigationBar(), BorderLayout.NORTH);
        frame.add(new SidebarMenu(), BorderLayout.WEST);
        frame.add(panel, BorderLayout.CENTER); // Switch the central panel only
        frame.add(new FooterBar(), BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
}
