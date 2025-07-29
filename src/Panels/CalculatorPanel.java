package Panels;

import Activity.Calculator;
import Models.CurrencyData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CalculatorPanel extends JPanel {
    private Calculator calculator;
    public CalculatorPanel() throws IOException, URISyntaxException, InterruptedException {
        setBackground(Color.LIGHT_GRAY);
        calculator = new Calculator();
        CurrencyData currency = calculator.getCurrencyInfo("usd");
        add(new JLabel("Calculator Panel\n " + currency.getName() + currency.getAsk()));
    }
}
