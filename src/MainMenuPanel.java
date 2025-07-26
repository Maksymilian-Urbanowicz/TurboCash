import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private int x = 0;
    private int y = 0;
    private JPanel cardsPanel;      // panel z CardLayout do przełączania
    private CardLayout cardLayout;  // layout zarządzający kartami

    public MainMenuPanel(JPanel cardsPanel, CardLayout cardLayout) {
        this.cardsPanel = cardsPanel;
        this.cardLayout = cardLayout;
        setLayout(new GridBagLayout());
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(button("Calculator", "calculator", new CalculatorPanel()), gbc);
        add(button("Chart", "chart", new ChartPanl()), gbc);
        add(button("Table", "tabel", new TablePanel()), gbc);
        add(button("resume", "resume", new ResumePanel()), gbc);
    }

    /**
     * Dodaje nowy panel i przycisk do panelu nawigacji
     *  * @param name - unikalna nazwa panelu w CardLayout
     * @param displayName - tekst na przycisku
     * @param panel - JPanel do wyświetlenia
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
