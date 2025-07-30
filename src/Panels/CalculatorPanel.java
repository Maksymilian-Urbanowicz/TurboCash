package Panels;

import Activity.Calculator;
import Models.Colors;
import Models.CurrencyData;
import Models.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CalculatorPanel extends JPanel {
    private Calculator calculator;

    public CalculatorPanel() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Colors.BACKGROUND); // jaśniejszy szary
        setLayout(new GridBagLayout());

        calculator = new Calculator();
        String[] currencyTab = {"usd", "eur", "gbp", "chf", "pln"};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Currency Calculator ");
        title.setFont(Fonts.TITLE_FONT);
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
        labelFrom.setFont(Fonts.LABEL_FONT);
        add(labelFrom, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy = 1;
        JComboBox comboFrom = new JComboBox(currencyTab);
        comboFrom.setFont(Fonts.LABEL_FONT);
        comboFrom.setBackground(Color.WHITE);
        comboFrom.setPreferredSize(new Dimension(145, 25));
        add(comboFrom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel labelTo = new JLabel("To ");
        labelTo.setFont(Fonts.LABEL_FONT);
        add(labelTo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JComboBox comboTo = new JComboBox(currencyTab);
        comboTo.setFont(Fonts.LABEL_FONT);
        comboTo.setBackground(Color.WHITE);
        comboTo.setPreferredSize(new Dimension(145, 25));
        add(comboTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel labelAmount = new JLabel("Amount ");
        labelAmount.setFont(Fonts.LABEL_FONT);
        add(labelAmount, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField tfAmount = new JTextField(10);
        tfAmount.setFont(Fonts.LABEL_FONT);
        tfAmount.setPreferredSize(new Dimension(100, 25));
        add(tfAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Calculate");
        button.setFont(Fonts.LABEL_FONT);
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
        labelError.setFont(Fonts.LABEL_FONT);
        labelError.setForeground(Colors.ERROR);

        add(labelError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel labelExchange = new JLabel(" ");
        labelExchange.setFont(Fonts.LABEL_FONT);
        add(labelExchange, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel labelCourse = new JLabel(" ");
        labelCourse.setFont(Fonts.LABEL_FONT);
        add(labelCourse, gbc);

        button.addActionListener(e -> {
            try {
                boolean ask = false, bid = false, mixed1 = false, mixed2 = false;
                CurrencyData currencyFrom=null,currencyTo = null;
                if(comboFrom.getSelectedItem().toString() != "pln"){
                    currencyFrom = calculator.getCurrencyInfo(comboFrom.getSelectedItem().toString());
                    mixed1 = true;
                }else{
                    ask = true;
                }

                if(comboTo.getSelectedItem().toString() != "pln"){
                    currencyTo = calculator.getCurrencyInfo(comboTo.getSelectedItem().toString());
                    mixed2 = true;
                }else{
                    bid = true;
                }

                if(comboTo.getSelectedItem().toString() == comboFrom.getSelectedItem().toString()){
                    ask = true;
                    bid = true;
                }

                if (bid == true && ask == true) {
                    labelError.setText("! ! ! wrong currency selected ! ! !");
                    throw new Exception("wrong currency selected");
                }

                if (tfAmount.getText().equals("") || !tfAmount.getText().matches("\\d+") || Double.parseDouble(tfAmount.getText())<=0) {
                    labelError.setText("! ! ! amount not entered ! ! !");
                    throw new Exception("amount missing");
                }

                double amount = Double.parseDouble(tfAmount.getText());
                double exchange = 0;
                double course = 0;

                if(mixed1 && mixed2){
                    course = currencyFrom.getBid()/currencyTo.getAsk();
                    exchange = amount * course;
                }

                if(ask && currencyTo!=null){
                    course = currencyTo.getAsk();
                    exchange = amount/currencyTo.getAsk();
                }

                if(bid && currencyFrom!=null){
                    course = currencyFrom.getBid();
                    exchange = amount*currencyFrom.getBid();
                }

                labelExchange.setText("Exchanged: "+String.format("%.2f", exchange));
                labelCourse.setText("Course: "+String.format("%.2f", course));

            } catch (IOException | URISyntaxException | InterruptedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while fetching currency data.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
