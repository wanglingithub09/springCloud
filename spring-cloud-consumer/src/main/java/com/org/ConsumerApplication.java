package com.org;

import com.org.configure.routing.RibbonClientConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
/**
* @Author: WangLin
* @Description: 启动类
* @Date: 2018/11/20 10:44
*/
@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@RibbonClients(
        @RibbonClient(name = "spring-cloud-provider",configuration = RibbonClientConfigure.class)
)
@EnableAspectJAutoProxy//开启动态代理
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }


}
