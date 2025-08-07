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
        if (jsonArray.isEmpty()) {
            return new String[0][];
        }

        JSONObject data = jsonArray.getJSONObject(0);
        JSONArray rates = data.getJSONArray("rates");

        String[][] cols = new String[rates.length()][3];

        for (int i = 0; i < rates.length(); i++) {
            JSONObject rate = rates.getJSONObject(i);

            String currencyCode = rate.getString("code");
            String translatedName;
            try {
                translatedName = translatorAPI.translate(currencyCode);
            } catch (Exception e) {
                translatedName = "Unknown";
            }
            double midRate = rate.getDouble("mid");

            cols[i] = new String[]{ currencyCode, translatedName, String.format("%.2f", midRate)};
        }
        return cols;
    }
}
