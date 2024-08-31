// File: Invoice.java
package com.warehouse.models;

public class Invoice {
    private String orderId;
    private String customerName;
    private String itemName;
    private int quantity;
    private double price;
    private String date;
    private double totalAmount;

    public Invoice(String orderId, String customerName, String itemName, int quantity, double price, String date, double totalAmount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
