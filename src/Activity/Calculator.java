package Activity;

import Activity.API.NBPAPI;
import Models.CurrencyData;
import Panels.CalculatorPanel;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends Component {
    private NBPAPI nbp;

    public Calculator() throws IOException, URISyntaxException, InterruptedException {
        nbp = new NBPAPI();
    }

    public CurrencyData getCurrencyInfo(String code) throws IOException, URISyntaxException, InterruptedException {
        JSONObject data = nbp.getCurrency(code);
        CurrencyData currency = new CurrencyData(
                data.getString("currency"),
                data.getString("code"),
                data.getJSONArray("rates").getJSONObject(0).getDouble("bid"),
                data.getJSONArray("rates").getJSONObject(0).getDouble("ask")
        );

        return currency;
    }

    public Map calculate(String comboFrom, String comboTo, String tfAmount, JLabel labelError) throws IOException, URISyntaxException, InterruptedException {
        try {
            boolean ask = false, bid = false, mixed1 = false, mixed2 = false;
            CurrencyData currencyFrom=null,currencyTo = null;
            labelError.setText(" ");

            if(comboFrom != "pln"){
                currencyFrom = getCurrencyInfo(comboFrom);
                mixed1 = true;
            }else{
                ask = true;
            }

            if(comboTo != "pln"){
                currencyTo = getCurrencyInfo(comboTo);
                mixed2 = true;
            }else{
                bid = true;
            }

            if(comboTo == comboFrom){
                ask = true;
                bid = true;
            }

            if (bid == true && ask == true) {
                labelError.setText("! ! ! wrong currency selected ! ! !");
                throw new Exception("wrong currency selected");
            }

            if (tfAmount.equals("") || !tfAmount.matches("\\d+") || Double.parseDouble(tfAmount)<=0) {
                labelError.setText("! ! ! amount not entered ! ! !");
                throw new Exception("amount missing");
            }

            double amount = Double.parseDouble(tfAmount);
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
            Map map = new HashMap();
            map.put("exchange", exchange);
            map.put("course", course);

            return map;


        } catch (IOException | URISyntaxException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching currency data.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

}
