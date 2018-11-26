package com.org.configure.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
* @Author: WangLin
* @Description: 拦截指定url, 自定义拦截器，需要生成Bean，解决拦截器注入报null错误.
* @Date: 2018/11/21 17:20
*/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    @Bean
    public ApiInterceptor apiInterceptor(){
        return new ApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有url
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
