package Activity.API;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NBPAPI {
    private HttpClient client;
    private HttpRequest request;
    public NBPAPI(){
        this.client = HttpClient.newHttpClient();
        this.request = (HttpRequest) HttpRequest.newBuilder();
    }

    public HttpResponse<String> getCurrency(String currency) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
