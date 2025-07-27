package Components;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    GridBagConstraints gbc = new GridBagConstraints();
    public FooterPanel() {
        setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(5,0,5,0);
        add(new JLabel("Maksymilian Urbanowicz \u00A9 2025 TurboCash"), gbc);
    }
}

