package Panels;

import Activity.Table;
import Models.Colors;
import Models.Fonts;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class TablePanel extends JPanel {
    public TablePanel() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Colors.BACKGROUND);
        setLayout(new GridBagLayout());

        Table tbl = new Table();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //set card title
        JLabel title = new JLabel("Course table");
        title.setFont(Fonts.TITLE);
        title.setForeground(Colors.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        //data in table
        String[][] data = tbl.getDataTable();
        String[] columnNames = {"CODE", "NAME", "COURSE"};

        //create table; set data in ceil and header; set non-editable table
        JTable table = new JTable(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                return false;  // blokuje edycję wszystkich komórek
            }
        };

        //set table colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                table.setFont(Fonts.TABLE_CEIL);
                if(!isSelected) {
                    if(row % 2 == 0) {
                        c.setBackground(Colors.TABLE_ROW_EVEN);
                    }else{
                        c.setBackground(Colors.TABLE_ROW_ODD);
                    }
                }else {
                    c.setBackground(table.getSelectionBackground());
                }
                return c;
            }
        });

        //set font and color of header
        JTableHeader header = table.getTableHeader();
        header.setFont(Fonts.TABLE_HEADER);
        header.setForeground(Colors.TITLE);
        header.setBackground(Colors.TABLE_HEADER);

        //create scroller
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBackground(Colors.TABLE_HEADER);
        scroller.getViewport().setBackground(Colors.TABLE_HEADER);

        //set scroller colors
        scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(200, 200, 200); // kolor uchwytu
                this.trackColor = Colors.TABLE_HEADER; // tło paska
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Colors.TABLE_HEADER);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Colors.TABLE_HEADER);
                return button;
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scroller, gbc);



    }
}
