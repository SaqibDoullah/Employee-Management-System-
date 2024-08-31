// File: SalesReport.java
package com.warehouse.models;

public class SalesReport {
    private String dateRange;
    private int totalOrders;
    private double totalRevenue;

    public SalesReport(String dateRange, int totalOrders, double totalRevenue) {
        this.dateRange = dateRange;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
