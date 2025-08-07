package Activity;

import Activity.API.CurrencyNameTranslatorAPI;
import Activity.API.NBPAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class Summary {
    private NBPAPI nbp;
    private CurrencyNameTranslatorAPI translatorAPI;

    public Summary() {
        nbp = new NBPAPI();
        translatorAPI = new CurrencyNameTranslatorAPI();
    }

    public String[][] getDataTable(String dateFrom, String dateTo) throws IOException, URISyntaxException, InterruptedException {
        JSONArray jsonArray = nbp.getTableWithDate('a', dateFrom, dateTo);
        if (jsonArray.length() < 2) {
            throw new IllegalStateException("Expected at least 2 entries for the given date range.");
        }

        JSONObject dataFirst = jsonArray.getJSONObject(0);
        JSONObject dataLast = jsonArray.getJSONObject(jsonArray.length() - 1);
        JSONArray ratesFirst = dataFirst.getJSONArray("rates");
        JSONArray ratesLast = dataLast.getJSONArray("rates");

        int minLength = Math.min(ratesFirst.length(), ratesLast.length());
        String[][] cols = new String[minLength][3];

        for (int i = 0; i < minLength; i++) {
            JSONObject rateF = ratesFirst.getJSONObject(i);
            JSONObject rateL = ratesLast.getJSONObject(i);

            String code = rateF.getString("code");
            String name;
            try {
                name = translatorAPI.translate(code);
            } catch (Exception e) {
                name = "Unknown"; // fallback
            }

            double start = rateF.getDouble("mid");
            double end = rateL.getDouble("mid");
            double diff = ((end - start) / start) * 100;

            cols[i] = new String[]{ code, name, String.format("%+.2f%%", diff)};
        }

        return cols;
    }
}
