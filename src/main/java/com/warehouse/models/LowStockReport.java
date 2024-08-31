// File: LowStockReport.java
package com.warehouse.models;

public class LowStockReport {
    private String itemId;
    private String itemName;
    private int quantity;
    private int reorderLevel;

    public LowStockReport(String itemId, String itemName, int quantity, int reorderLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}
