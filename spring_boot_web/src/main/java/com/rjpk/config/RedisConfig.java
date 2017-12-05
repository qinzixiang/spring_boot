package com.rjpk.config;

import com.rjpk.core.commmon.redis.handler.SubRedisMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @ClassName RedisConfig
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 15:09
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getRedisPool().getMaxTotal());
        poolConfig.setMaxIdle(redisProperties.getRedisPool().getMaxIdle());
        poolConfig.setTestOnBorrow(redisProperties.getRedisPool().isTestOnBorrow());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setPassword(redisProperties.getPwd());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        log.info("redis配置信息,redisProperties={}", redisProperties.toString());
        log.info("redis配置信息,redisProperties.poolConfig={}", redisProperties.getRedisPool().toString());
        return jedisConnectionFactory;
    }

    /**
     * Redis模板
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(template.getStringSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 监听
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫chat 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic("finance_redis"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(SubRedisMessageHandler subRedisMessageHandler) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        return new MessageListenerAdapter(subRedisMessageHandler,"onMessage");
    }
}