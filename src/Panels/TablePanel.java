package Panels;

import Activity.Table;
import Models.Colors;
import Models.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class TablePanel extends JPanel {
    public TablePanel() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Colors.BACKGROUND); // jaśniejszy szary
        setLayout(new GridBagLayout());

        Table tbl = new Table();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Course table");
        title.setFont(Fonts.TITLE_FONT);
        title.setForeground(Colors.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        String[][] data = tbl.getDataTable();
        String[] columnNames = {"CODE", "NAME", "COURSE"};

        JTable table = new JTable(data, columnNames);
        JScrollPane scroller = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scroller, gbc);


    }
}
