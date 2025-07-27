package Components;

import Components.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class TemplatePanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
    public TemplatePanel(){
        setLayout(new BorderLayout());

        add(new MainMenuPanel(cardsPanel, cardLayout), BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
        add(new FooterPanel(), BorderLayout.SOUTH);
    }
}
