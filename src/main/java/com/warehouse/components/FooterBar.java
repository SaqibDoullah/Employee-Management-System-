// File: FooterBar.java
package com.warehouse.components;

import javax.swing.*;
import java.awt.*;

public class FooterBar extends JPanel {
    public FooterBar() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(new JLabel("Version 1.0.0"));
        this.add(new JLabel(" | Date: "));
        this.add(new JLabel(java.time.LocalDate.now().toString()));
    }
}
