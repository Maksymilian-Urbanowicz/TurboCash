package Components;

import Components.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class TemplatePanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
    public TemplatePanel() throws IOException, URISyntaxException, InterruptedException, ParseException {
        setLayout(new BorderLayout());

        add(new MainMenuPanel(cardsPanel, cardLayout), BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
        add(new FooterPanel(), BorderLayout.SOUTH);
    }
}
