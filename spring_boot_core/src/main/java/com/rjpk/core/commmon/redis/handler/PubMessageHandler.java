package com.rjpk.core.commmon.redis.handler;

import com.mljr.common.enums.GuavaCacheEnum;
import com.rjpk.core.commmon.redis.model.RedisMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName PubMessageHandler
 * @Description 发送消息
 * @Author xiangnan.xu
 * @DATE 2017/11/25 22:09
 */
@Component
public class PubMessageHandler {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public void sendMessage(Serializable message) {
        redisTemplate.convertAndSend("finance_redis", message);
    }

    public void sendMessage(GuavaCacheEnum guavaCacheEnum, Object key){
        RedisMessageVo<Object> messageVo = new RedisMessageVo<Object>();
        messageVo.setGuavaCacheEnum(guavaCacheEnum);
        messageVo.setKey(key);
        redisTemplate.convertAndSend("finance_redis", messageVo);
    }
}
