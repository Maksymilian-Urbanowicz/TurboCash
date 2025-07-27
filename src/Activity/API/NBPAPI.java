package Activity.API;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class NBPAPI {
    private HttpClient client;
    private HttpRequest request;
    public NBPAPI(){
        this.client = HttpClient.newHttpClient();
        this.request = (HttpRequest) HttpRequest.newBuilder();
    }
}
