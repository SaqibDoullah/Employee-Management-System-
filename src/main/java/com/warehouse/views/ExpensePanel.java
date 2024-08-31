// File: ExpensePanel.java
package com.warehouse.views;

import com.warehouse.models.Expense;
import com.warehouse.services.ExpenseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExpensePanel extends JPanel {
    private JTable expenseTable;
    private DefaultTableModel tableModel;
    private ExpenseService expenseService;

    public ExpensePanel() {
        this.setLayout(new BorderLayout());

        // Initialize ExpenseService
        expenseService = new ExpenseService();

        // Table model with column names
        String[] columns = {"Expense ID", "Description", "Amount", "Date"};
        tableModel = new DefaultTableModel(columns, 0);

        // Expense Table
        expenseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(expenseTable);

        // Add expense table to the center
        this.add(scrollPane, BorderLayout.CENTER);

        // Add Expense Panel at the bottom
        JPanel addExpensePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addExpensePanel.add(new JLabel("Expense ID:"));
        JTextField expenseIdField = new JTextField(5);
        addExpensePanel.add(expenseIdField);
        addExpensePanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField(15);
        addExpensePanel.add(descriptionField);
        addExpensePanel.add(new JLabel("Amount:"));
        JTextField amountField = new JTextField(5);
        addExpensePanel.add(amountField);
        addExpensePanel.add(new JLabel("Date:"));
        JTextField dateField = new JTextField(10);
        addExpensePanel.add(dateField);

        JButton addButton = new JButton("Add Expense");
        JButton deleteButton = new JButton("Delete Expense");

        addExpensePanel.add(addButton);
        addExpensePanel.add(deleteButton);

        // Add the expense panel to the bottom
        this.add(addExpensePanel, BorderLayout.SOUTH);

        // Load existing expenses from the database
        loadExpenses();

        // Add expense button action
        addButton.addActionListener(e -> {
            // Create expense
            Expense expense = new Expense(
                    expenseIdField.getText(),
                    descriptionField.getText(),
                    Double.parseDouble(amountField.getText()),
                    dateField.getText()
            );

            // Add expense to the expense service
            expenseService.addExpense(expense);

            // Add expense to the table model
            tableModel.addRow(new Object[]{
                    expense.getExpenseId(),
                    expense.getDescription(),
                    expense.getAmount(),
                    expense.getDate()
            });

            // Clear input fields
            clearInputFields(expenseIdField, descriptionField, amountField, dateField);
        });

        // Delete expense button action
        deleteButton.addActionListener(e -> {
            int selectedRow = expenseTable.getSelectedRow();
            if (selectedRow >= 0) {
                // Get selected expense ID
                String expenseId = (String) tableModel.getValueAt(selectedRow, 0);

                // Delete expense from the expense service
                expenseService.deleteExpense(expenseId);

                // Remove expense from the table model
                tableModel.removeRow(selectedRow);

                // Clear input fields
                clearInputFields(expenseIdField, descriptionField, amountField, dateField);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an expense to delete.");
            }
        });

        // Populate input fields when a row is selected
        expenseTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && expenseTable.getSelectedRow() != -1) {
                int selectedRow = expenseTable.getSelectedRow();
                expenseIdField.setText((String) tableModel.getValueAt(selectedRow, 0));
                descriptionField.setText((String) tableModel.getValueAt(selectedRow, 1));
                amountField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                dateField.setText((String) tableModel.getValueAt(selectedRow, 3));
            }
        });
    }

    private void loadExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        for (Expense expense : expenses) {
            tableModel.addRow(new Object[]{
                    expense.getExpenseId(),
                    expense.getDescription(),
                    expense.getAmount(),
                    expense.getDate()
            });
        }
    }

    private void clearInputFields(JTextField expenseIdField, JTextField descriptionField, JTextField amountField, JTextField dateField) {
        expenseIdField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        dateField.setText("");
    }
}
