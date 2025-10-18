package tests;

import Activity.API.NBPAPI;
import org.junit.Before;

import java.net.http.HttpClient;

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


}
