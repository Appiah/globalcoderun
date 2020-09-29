package flutterwave.waya.demo0;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/flutterwave")
public class FlutterwaveController {

    @Get("/")
    /*public HttpStatus index() {
        return HttpStatus.OK;
    }*/
    public String index() {
    	return "Run the deposit endpoint";
    	
    }
}