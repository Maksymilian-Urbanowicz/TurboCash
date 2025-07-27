package Panels;

import javax.swing.*;
import java.awt.*;

import static javax.swing.text.StyleConstants.setBackground;

public class ChartPanl extends JPanel{ //show data on line chart
    public ChartPanl(){
        setBackground(Color.LIGHT_GRAY);
        add(new JLabel("Chart panel"));
    }
}
