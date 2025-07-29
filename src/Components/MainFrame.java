package Components;

import Components.TemplatePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainFrame extends JFrame {
    public MainFrame() throws IOException, URISyntaxException, InterruptedException {
        setTitle("TurboCash – your currency courses");
        setSize(1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new TemplatePanel(), BorderLayout.CENTER);
        setVisible(true);

    }

}
