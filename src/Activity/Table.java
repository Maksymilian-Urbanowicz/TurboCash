package Activity;

import Activity.API.CurrencyNameTranslatorAPI;
import Activity.API.NBPAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class Table {
    private NBPAPI nbp;
    private CurrencyNameTranslatorAPI translatorAPI;

    public Table() {
        nbp = new NBPAPI();
        translatorAPI = new CurrencyNameTranslatorAPI();
    }

    public String[][] getDataTable() throws IOException, URISyntaxException, InterruptedException {
        JSONArray jsonArray = nbp.getTableActual('a');
        JSONObject data = jsonArray.getJSONObject(0);
        JSONArray rates = data.getJSONArray("rates");

        String[][] cols = new String[rates.length()][3];
        for (int i = 0; i < rates.length(); i++) {
            JSONObject rate = rates.getJSONObject(i);
            cols[i] = new String[]{
                    rate.getString("code"),
                    translatorAPI.translate(rate.getString("code")),
                    String.format("%.2f",rate.getDouble("mid"))
            };
        }
        return cols;
    }
}
