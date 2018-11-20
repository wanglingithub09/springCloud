package com.org.configure.routing;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *  客户端请求-路由-负载均衡配置
 */
@Configuration
public class RibbonClientConfigure {

    /**
     * 路由-方式
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
        // return new WeightedResponseTimeRule();    //加权权重
        //return new RetryRule();                    //带有重试机制的轮训
        //return new RandomRule();                   //随机
        //return new RoundRobinRule();                //轮训
        //return new TestRule();                     //自定义规则
    }

    /**
     * 客户端调用
     * LoadBalanced开启负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        //60s连接超时时间、读取超时
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30*1000);
        requestFactory.setReadTimeout(10*1000);
        return new RestTemplate(requestFactory);
    }
}
