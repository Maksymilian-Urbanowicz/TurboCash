package Activity;

import Activity.API.NBPAPI;
import Models.CurrencyData;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Currency;
import java.util.Map;

public class Calculator {
    private NBPAPI nbp;

    public Calculator() {
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


}
