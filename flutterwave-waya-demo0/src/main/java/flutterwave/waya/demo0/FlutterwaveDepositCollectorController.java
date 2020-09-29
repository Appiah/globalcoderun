package flutterwave.waya.demo0;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/flutterwavedepositcollector")
public class FlutterwaveDepositCollectorController {

    @Get("/")
    /*public HttpStatus index() {
        return HttpStatus.OK;
    }*/
    public String endpoint_deposit_collector() {
    	return "Run flutterwaveDepositCollector!";
    }
}