package Panels;

import Activity.Chart;
import Models.Colors;
import Models.Fonts;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static javax.swing.text.StyleConstants.setBackground;

public class ChartPanl extends JPanel{
    private Chart chart;
    private DefaultCategoryDataset dataset;
    public ChartPanl() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Colors.BACKGROUND);
        setLayout(new GridBagLayout());

        chart = new Chart();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Currency Chart");
        title.setFont(Fonts.TITLE);
        title.setForeground(Colors.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        String[] currencyTab = {"usd", "eur", "gbp", "chf"};

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel labelCurrency = new JLabel("Choice currency ");
        labelCurrency.setFont(Fonts.LABEL);
        add(labelCurrency, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JComboBox comboCurrency = new JComboBox(currencyTab);
        comboCurrency.setFont(Fonts.INPUT_CONTROLS);
        comboCurrency.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        comboCurrency.setPreferredSize(new Dimension(145, 25));
        add(comboCurrency, gbc);



        String[][] data = chart.setChartData(comboCurrency.getSelectedItem().toString(), 7);
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < data.length; i++) {
            dataset.addValue(Double.parseDouble(data[i][1]), comboCurrency.getSelectedItem().toString(), data[i][0]);
        }

        //Line Chart
        JFreeChart chartLine = ChartFactory.createLineChart(
                "Wykres waluty: "+comboCurrency.getSelectedItem(), // chart title
                "Date",                 // title axis Y
                "Course",                  // title axis X
                dataset,                    // data
                PlotOrientation.VERTICAL,
                true,                       // legend
                true,                       // tooltip
                false                       // URL
        );

        ChartPanel chartPanel = new ChartPanel(chartLine);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(chartPanel, gbc);

        comboCurrency.addActionListener(e->{
            try {
                String[][] data2 = chart.setChartData(comboCurrency.getSelectedItem().toString(), 7);
                dataset.clear();
                for (int i = 0; i < data2.length; i++) {
                    dataset.addValue(Double.parseDouble(data2[i][1]), comboCurrency.getSelectedItem().toString(), data2[i][0]);
                }
                chartLine.getTitle().setText("Wykres waluty: " + comboCurrency.getSelectedItem());
                chartPanel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
