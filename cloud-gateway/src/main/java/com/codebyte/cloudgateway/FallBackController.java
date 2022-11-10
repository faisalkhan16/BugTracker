package com.codebyte.cloudgateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallBackController {

    @GetMapping("/userServiceFallBack")
    @HystrixCommand(fallbackMethod="userServiceFallBackMethod")
    public String userServiceFallBack(){
        throw new RuntimeException("Not Available");
    }

    @GetMapping("/bugServiceFallBack")
    @HystrixCommand(fallbackMethod="bugServiceFallBackMethod")
    public String bugServiceFallBack(){
        throw new RuntimeException("Not Available");
    }

    @GetMapping("/productServiceFallBack")
    @HystrixCommand(fallbackMethod="productServiceFallBackMethod")
    public String productServiceFallBack(){
        throw new RuntimeException("Not Available");
    }



    public String userServiceFallBackMethod(){
        return "User Service taking long time than expected, Please contact system administrator";
    }


    public String bugServiceFallBackMethod(){
        return "Bug Service taking long time than expected, Please contact system administrator";
    }

    public String productServiceFallBackMethod(){
        return "Product Service taking long time than expected, Please contact system administrator";
    }
}
