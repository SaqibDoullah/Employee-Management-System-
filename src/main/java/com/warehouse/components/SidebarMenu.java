// File: SidebarMenu.java
package com.warehouse.components;

import javax.swing.*;
import java.awt.*;

public class SidebarMenu extends JPanel {
    public SidebarMenu() {
        this.setLayout(new GridLayout(6, 1));
        this.add(new JButton("Dashboard"));
        this.add(new JButton("Inventory"));
        this.add(new JButton("Orders"));
        this.add(new JButton("Accounting"));
        this.add(new JButton("Users"));
        this.add(new JButton("Reports"));
    }
}
