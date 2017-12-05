package com.rjpk.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RedisProperties
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 15:10
 */
@Setter
@Getter
@Component
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "redis.server")
@ToString
public class RedisProperties {
    private String host;

    private String pwd;

    private int port;

    @Resource
    private RedisPool redisPool;
}
