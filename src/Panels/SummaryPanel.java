package Panels;

import Activity.Summary;
import Models.Colors;
import Models.Fonts;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeListener;

public class SummaryPanel extends JPanel{ //show the currency time resume
    public SummaryPanel() throws IOException, URISyntaxException, InterruptedException, ParseException {
        setBackground(Colors.BACKGROUND);
        setLayout(new GridBagLayout());

        Summary smr = new Summary();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //set card title
        JLabel title = new JLabel("Summary table");
        title.setFont(Fonts.TITLE);
        title.setForeground(Colors.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Colors.BACKGROUND);
        btnPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnPanel, gbc);

        JPanel datePanel = new JPanel();
        datePanel.setBackground(Colors.BACKGROUND);
        datePanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        btnPanel.add(datePanel, gbc);

        JLabel labelDateFrom = new JLabel("From ");
        labelDateFrom.setFont(Fonts.LABEL);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 10, 0, 5);
        datePanel.add(labelDateFrom, gbc);

        JDateChooser dateFrom = new JDateChooser();

        //set dateFrom range
        Date[] dateRangeFrom = setDateFromRange(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-02"));
        dateFrom.setSelectableDateRange(dateRangeFrom[0], dateRangeFrom[1]);

        dateFrom.setPreferredSize(new Dimension(150, 30));
        dateFrom.setDateFormatString("yyyy-MM-dd");
        dateFrom.setFont(Fonts.INPUT_CONTROLS);
        dateFrom.setForeground(Colors.INPUT_CONTROLS_FONT);
        dateFrom.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 15);
        datePanel.add(dateFrom, gbc);

        JLabel labelDateTo = new JLabel("To ");
        labelDateTo.setFont(Fonts.LABEL);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 10, 0, 5);
        datePanel.add(labelDateTo, gbc);

        JDateChooser dateTo = new JDateChooser();
        //set dateTo range
        Date[] dateRangeTo = setDateToRange(dateFrom.getDate());
        dateTo.setSelectableDateRange(dateRangeTo[0], dateRangeTo[1]);

        dateTo.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        dateTo.setPreferredSize(new Dimension(150, 30));
        dateTo.setDateFormatString("yyyy-MM-dd");
        dateTo.setFont(Fonts.INPUT_CONTROLS);
        dateTo.setForeground(Colors.INPUT_CONTROLS_FONT);
        dateTo.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 15);
        datePanel.add(dateTo, gbc);

        JButton btnSetDates = new JButton("set dates");
        btnSetDates.setFont(Fonts.BUTTON);
        btnSetDates.setBackground(Colors.BUTTON_BACKGROUND);
        btnSetDates.setForeground(Colors.BUTTON_FONT);
        btnSetDates.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 0, 0);
        datePanel.add(btnSetDates, gbc);

        JPanel seriesPanel = new JPanel();
        seriesPanel.setBackground(Colors.BACKGROUND);
        seriesPanel.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 50, 0, 0);
        btnPanel.add(seriesPanel, gbc);

        JButton btn7day = new JButton("7day");
        btn7day.setFont(Fonts.BUTTON);
        btn7day.setBackground(Colors.BUTTON_BACKGROUND);
        btn7day.setForeground(Colors.BUTTON_FONT);
        btn7day.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 5, 0, 5);
        seriesPanel.add(btn7day, gbc);

        JButton btn14day = new JButton("14day");
        btn14day.setFont(Fonts.BUTTON);
        btn14day.setBackground(Colors.BUTTON_BACKGROUND);
        btn14day.setForeground(Colors.BUTTON_FONT);
        btn14day.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 5, 0, 5);
        seriesPanel.add(btn14day, gbc);

        JButton btn1month = new JButton("1 month");
        btn1month.setFont(Fonts.BUTTON);
        btn1month.setBackground(Colors.BUTTON_BACKGROUND);
        btn1month.setForeground(Colors.BUTTON_FONT);
        btn1month.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 5, 0, 5);
        seriesPanel.add(btn1month, gbc);

        //data in table
        String[][] data = smr.getDataTable(
                new SimpleDateFormat("yyyy-MM-dd").format(yesterday()),
                new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        );

        String[] columnNames = {"CODE", "NAME", "COURSE"};

        //create table; set data in ceil and header; set non-editable table
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // set non-editable table
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
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scroller, gbc);

        dateFrom.addPropertyChangeListener("date", evt -> {

            //update dateTo range
            Date[] dateRangeTo2 = setDateToRange(dateFrom.getDate());
            dateTo.setSelectableDateRange(dateRangeTo2[0], dateRangeTo2[1]);
            dateTo.setDate(null);
        });

        btnSetDates.addActionListener(evt -> {
            try {
                String[][] noweDane = smr.getDataTable(
                        new SimpleDateFormat("yyyy-MM-dd").format(dateFrom.getDate()),
                        new SimpleDateFormat("yyyy-MM-dd").format(dateTo.getDate())
                );

                DefaultTableModel newModel = new DefaultTableModel(noweDane, columnNames);
                table.setModel(newModel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private Date[] setDateFromRange(Date startDate) {
        Date endDate = new Date();
        return new Date[]{startDate, endDate};
    }

    private Date[] setDateToRange(Date startDate) {
        Calendar cal = Calendar.getInstance();
        if(startDate != null) {
            cal.setTime(startDate);
            cal.add(Calendar.DAY_OF_YEAR, 1);
            Date start = cal.getTime();  // the day after startDate

            cal.add(Calendar.DAY_OF_YEAR, 92);  // add 93 days to startDate
            Date end = cal.getTime();

            Date today = new Date();

            if (start.after(today)) {
                start = today;
            }

            if (end.after(today)) {
                end = today;
            }

            return new Date[]{start, end};
        }
        return new Date[]{null,null};
    }

    private Date yesterday() {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return cal.getTime();
    }
}
