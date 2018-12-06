package com.org.configure.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.HashMap;
import java.util.Map;
/**
* @Author: WangLin
* @Description: 配置Lettuce连接池
* @Date: 2018/11/20 10:17
*/
@Configuration
@Order(0)
public class RedisFactoryConfig {

    @Autowired
    private Environment environment;

    @Bean
    public RedisConnectionFactory myLettuceConnectionFactory() {
        Map<String, Object> source = new HashMap<>();
        source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
        source.put("spring.redis.password", environment.getProperty("spring.redis.password"));
        source.put("spring.redis.cluster.timeout", environment.getProperty("spring.redis.cluster.timeout"));
        source.put("spring.redis.cluster.max-redirects", environment.getProperty("spring.redis.cluster.max-redirects"));
        source.put("spring.redis.lettuce.pool.max-active", environment.getProperty("spring.redis.lettuce.pool.max-active"));
        source.put("spring.redis.lettuce.pool.min-idle", environment.getProperty("spring.redis.lettuce.pool.min-idle"));
        source.put("spring.redis.lettuce.pool.max-idle", environment.getProperty("spring.redis.lettuce.pool.max-idle"));
        RedisClusterConfiguration redisClusterConfiguration;
        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        return new LettuceConnectionFactory(redisClusterConfiguration);

    }
}
