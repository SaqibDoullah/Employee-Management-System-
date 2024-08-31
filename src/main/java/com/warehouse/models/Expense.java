// File: Expense.java
package com.warehouse.models;

public class Expense {
    private String expenseId;
    private String description;
    private double amount;
    private String date;

    public Expense(String expenseId, String description, double amount, String date) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
