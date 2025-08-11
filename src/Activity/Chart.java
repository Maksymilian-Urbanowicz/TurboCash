package Activity;

import Activity.API.NBPAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Chart {

    private NBPAPI nbp;

    public Chart() {
        nbp = new NBPAPI();
    }


    public String[][] setChartData(String code, int amount) throws IOException, URISyntaxException, InterruptedException {

        JSONObject jsonObject = nbp.getCurrencySeries(code,amount);
        if (jsonObject.isEmpty()) {
            return new String[0][];
        }

        JSONArray rates = jsonObject.getJSONArray("rates");
        String[][] cols = new String[rates.length()][3];

        for (int i = 0; i < rates.length(); i++) {
            JSONObject rate = rates.getJSONObject(i);

            String date = rate.getString("effectiveDate");
            double midRate = rate.getDouble("mid");

            cols[i] = new String[]{ date, String.format(Locale.US, "%.2f", midRate)};
        }
        return cols;
    }

}
