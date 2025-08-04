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
}
