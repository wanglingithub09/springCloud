package com.org.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
/**
* @Author: WangLin
* @Description: redis mq消息监听
* @Date: 2018/12/6 9:14
*/
@Component
public class Receiver implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        String msg = redisSerializer.deserialize(message.getBody());
        logger.info("接收到MQ消息"+msg);
    }
}
