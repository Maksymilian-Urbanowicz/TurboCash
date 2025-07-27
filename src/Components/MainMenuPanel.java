package Components;

import Panels.CalculatorPanel;
import Panels.ChartPanl;
import Panels.SummaryPanel;
import Panels.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private int x = 0;
    private int y = 0;
    private JPanel cardsPanel;      // panel with CardLayout to switch
    private CardLayout cardLayout;  // layout manage cards

    /**
     * Constructs the main menu panel with navigation buttons.
     * @param cardsPanel - the container JPanel using CardLayout to hold multiple views
     * @param cardLayout - the CardLayout manager used to switch between views in cardsPanel
     */
    public MainMenuPanel(JPanel cardsPanel, CardLayout cardLayout) {
        this.cardsPanel = cardsPanel;
        this.cardLayout = cardLayout;
        setLayout(new GridBagLayout());
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(button("Calculator", "calculator", new CalculatorPanel()), gbc);
        add(button("Chart", "chart", new ChartPanl()), gbc);
        add(button("Table", "tabel", new TablePanel()), gbc);
        add(button("resume", "resume", new SummaryPanel()), gbc);
    }

    /**
     * Adds a new JPanel and corresponding JButton.
     * @param name - unique panel name in CardLayout
     * @param displayName - JButton display text
     * @param panel - JPanel to display
     * @return the created JButton
     */
    public JButton button(String name, String displayName, JPanel panel) {

        cardsPanel.add(panel, name);

        JButton btn = new JButton(displayName);
        btn.addActionListener(e -> cardLayout.show(cardsPanel, name));

        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridx = x++;
        gbc.gridy = y;

        return btn;


    }
}
