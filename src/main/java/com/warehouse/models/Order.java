// File: Order.java
package com.warehouse.models;

public class Order {
    private String orderId;
    private String customerName;
    private String itemId;
    private int quantity;
    private String orderDate;
    private String status;

    public Order(String orderId, String customerName, String itemId, int quantity, String orderDate, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
