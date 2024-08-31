// File: DashboardPanel.java
package com.warehouse.views;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Dashboard Overview", JLabel.CENTER), BorderLayout.CENTER);
    }
}
