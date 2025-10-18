package tests;

import Activity.API.NBPAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class NBPAPITest {
    private HttpClient mockClient;
    private NBPAPI api; //testes class

    public NBPAPITest() {

    }

    @Before
    public void setUp() throws Exception {
        mockClient = HttpClient.newHttpClient();
        api = new NBPAPI(){
            {
                this.client = mockClient; //set client on mock
            }
        };
    }

    @Test
    public void testGetCurrencySeries_returnsJSONObject() throws Exception {
        JSONObject result = api.getCurrencySeries("USD", 5);

        assertNotNull("Odpowiedź nie powinna być nullem", result);
        assertTrue("Powinien istnieć atrybut rates", result.has("rates"));

        JSONObject rate = result.getJSONArray("rates").getJSONObject(0);
        assertTrue("Pole mid powinno być dodatnie", rate.getDouble("mid") > 0);
    }

    @Test
    public void testGetTableActual_returnsJSONArray() throws Exception {
        JSONArray result = api.getTableActual('A');

        assertNotNull(result);
        assertTrue("Odpowiedź powinna zawierać przynajmniej jeden element", result.length() > 0);

        JSONObject firstObject = result.getJSONObject(0);
        assertTrue("Powinien istnieć atrybut rates", firstObject.has("rates"));
    }

    @Test
    public void testGetTableSeries_returnsJSONArray() throws Exception {
        JSONArray result = api.getTableSeries('A', 5);

        assertNotNull(result);
        assertTrue("Odpowiedź powinna zawierać przynajmniej jeden element", result.length() > 0);

        JSONObject firstObject = result.getJSONObject(0);
        assertTrue("Powinien istnieć atrybut rates", firstObject.has("rates"));
    }
    
    @Test
    public void testGetTableWithDate_returnsJSONArray() throws Exception {
        String jsonArrayResponse = """
                [
                  {
                    "table": "A",
                    "no": "200/A/NBP/2025",
                    "effectiveDate": "2025-10-18",
                    "rates": [
                      {"currency": "dolar amerykański", "code": "USD", "mid": 4.2}
                    ]
                  }
                ]
                """;

        JSONArray result = api.getTableWithDate('a', "2025-10-01", "2025-10-18");

        //System.out.println(result.toString());

        assertNotNull(result);
        assertTrue("Odpowiedź powinna zawierać przynajmniej jeden element", result.length() > 0);

// sprawdzamy przykładowe pole w pierwszym obiekcie
        JSONObject firstObject = result.getJSONObject(0);
        double mid = firstObject.getJSONArray("rates").getJSONObject(0).getDouble("mid");
        assertTrue("Kurs waluty powinien być większy od 0", mid > 0);
    }


}
