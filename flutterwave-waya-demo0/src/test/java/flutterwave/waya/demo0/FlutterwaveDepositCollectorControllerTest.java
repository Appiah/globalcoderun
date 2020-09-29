package flutterwave.waya.demo0;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class FlutterwaveDepositCollectorControllerTest {

    @Inject
    @Client("/")
    //EmbeddedServer embeddedServer;
    RxHttpClient client;
    
    @Test
    public void test_endpoint_deposit_collector() throws Exception {
        /*try(RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/flutterwaveDepositCollector").status());
        }*/
    	HttpRequest<String> request = HttpRequest.GET("/flutterwavedepositcollector");
    	String body = client.toBlocking().retrieve(request);
    	
    	assertNotNull(body);
    	assertEquals("Run flutterwaveDepositCollector!", body);
    }
}
