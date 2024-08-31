// File: InventoryItem.java
package com.warehouse.models;

public class InventoryItem {
    private String itemId;
    private String itemName;
    private int quantity;
    private String location;
    private int reorderLevel;

    public InventoryItem(String itemId, String itemName, int quantity, String location, int reorderLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}
