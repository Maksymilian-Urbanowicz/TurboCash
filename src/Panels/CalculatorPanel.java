package Panels;

import Activity.Calculator;
import Models.Colors;
import Models.CurrencyData;
import Models.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class CalculatorPanel extends JPanel {
    private Calculator calculator;

    public CalculatorPanel() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Colors.BACKGROUND);
        setLayout(new GridBagLayout());

        calculator = new Calculator();
        String[] currencyTab = {"usd", "eur", "gbp", "chf", "pln"};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Currency Calculator ");
        title.setFont(Fonts.TITLE);
        title.setForeground(Colors.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelFrom = new JLabel("From ");
        labelFrom.setFont(Fonts.LABEL);
        add(labelFrom, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy = 1;
        JComboBox comboFrom = new JComboBox(currencyTab);
        comboFrom.setFont(Fonts.INPUT_CONTROLS);
        comboFrom.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        comboFrom.setPreferredSize(new Dimension(145, 25));
        add(comboFrom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel labelTo = new JLabel("To ");
        labelTo.setFont(Fonts.LABEL);
        add(labelTo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JComboBox comboTo = new JComboBox(currencyTab);
        comboTo.setSelectedIndex(1);
        comboTo.setFont(Fonts.INPUT_CONTROLS);
        comboTo.setBackground(Colors.INPUT_CONTROLS_BACKGROUND);
        comboTo.setPreferredSize(new Dimension(145, 25));
        add(comboTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel labelAmount = new JLabel("Amount ");
        labelAmount.setFont(Fonts.LABEL);
        add(labelAmount, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField tfAmount = new JTextField(10);
        tfAmount.setFont(Fonts.INPUT_CONTROLS);
        tfAmount.setPreferredSize(new Dimension(100, 25));
        add(tfAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Calculate");
        button.setFont(Fonts.BUTTON);
        button.setBackground(Colors.BUTTON_BACKGROUND);
        button.setForeground(Colors.BUTTON_FONT);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 35));
        add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel labelError = new JLabel(" ");
        labelError.setFont(Fonts.LABEL);
        labelError.setForeground(Colors.ERROR);

        add(labelError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel labelExchange = new JLabel(" ");
        labelExchange.setFont(Fonts.LABEL);
        add(labelExchange, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel labelCourse = new JLabel(" ");
        labelCourse.setFont(Fonts.LABEL);
        add(labelCourse, gbc);

        button.addActionListener(e -> {
            try {
                Map data = calculator.calculate(comboFrom.getSelectedItem().toString(), comboTo.getSelectedItem().toString(), tfAmount.getText(), labelError);
                labelExchange.setText("Exchanged: "+String.format("%.2f", data.get("exchange")));
                labelCourse.setText("Course: "+String.format("%.2f", data.get("course")));
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
