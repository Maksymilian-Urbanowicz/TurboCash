package Activity.API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class NBPAPI {
    protected HttpClient client;

    public NBPAPI(){
        this.client = HttpClient.newHttpClient();
    }

    public JSONObject getCurrency(String code) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/rates/c/"+code+"/today/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONObject jsonObject = new JSONObject(response.body());
            //System.out.println(jsonObject.toString());
            return new JSONObject(response.body());
        }else{
            return new JSONObject();
        }
    }

    public JSONArray getCurrencyWithDate(String code, String dateFrom, String dateTo) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/rates/a/"+code+"/"+dateFrom+"/"+dateTo+"/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONArray jsonArray = new JSONArray(response.body());
            //System.out.println(jsonArray.toString());
            return new JSONArray(response.body());
        }else{
            return new JSONArray();
        }
    }

    public JSONObject getCurrencySeries(String code, int number) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/rates/a/"+code+"/last/"+number+"/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONObject jsonObject = new JSONObject(response.body());
            //System.out.println(jsonObject.toString());
            return new JSONObject(response.body());
        }else{
            return new JSONObject();
        }
    }

    public JSONArray getTableActual(char kind) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/tables/"+kind+"/last/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONArray jsonArray = new JSONArray(response.body());
            //System.out.println(jsonArray.toString());
            return new JSONArray(response.body());
        }else{
            return new JSONArray();
        }
    }


    public JSONArray getTableSeries(char kind, int number) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/tables/"+kind+"/last/"+number+"/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONArray jsonArray = new JSONArray(response.body());
            //System.out.println(jsonArray.toString());
            return new JSONArray(response.body());
        }else{
            return new JSONArray();
        }
    }

    public JSONArray getTableWithDate(char kind, String dateFrom, String dateTo) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/tables/"+kind+"/"+dateFrom+"/"+dateTo+"/?format=json"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body()!=null){
            //JSONArray jsonArray = new JSONArray(response.body());
            //System.out.println(jsonArray.toString());
            return new JSONArray(response.body());
        }else{
            return new JSONArray();
        }
    }
}
