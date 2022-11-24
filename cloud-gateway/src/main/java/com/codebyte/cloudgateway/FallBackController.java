package com.codebyte.cloudgateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallBackController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack(){
        return "User Service taking long time than expected, Please contact system administrator";
    }

    @GetMapping("/bugServiceFallBack")
    public String bugServiceFallBack(){
        log.info("bugServiceFallback");
        return "Bug Service taking long time than expected, Please contact system administrator";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack(){
        return "Product Service taking long time than expected, Please contact system administrator";
    }

}
