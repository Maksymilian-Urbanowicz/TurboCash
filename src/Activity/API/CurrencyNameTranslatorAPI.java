package Activity.API;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyNameTranslatorAPI {
    private HttpClient client;

    public CurrencyNameTranslatorAPI() {
        this.client = HttpClient.newHttpClient();
    }

    public String translate(String code) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://openexchangerates.org/api/currencies.json")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        return json.getString(code);
    }
}
