package com.org;

import com.org.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//加上网关注解
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZullGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZullGatewayApplication.class,args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
