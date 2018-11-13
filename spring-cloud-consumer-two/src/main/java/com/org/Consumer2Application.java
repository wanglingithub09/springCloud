package com.org;

import com.org.configure.RibbonClientConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@RibbonClients(
        @RibbonClient(name = "spring-cloud-provider",configuration = RibbonClientConfigure.class)
)
public class Consumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer2Application.class,args);
    }
}
