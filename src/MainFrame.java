import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
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
