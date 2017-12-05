package com.rjpk.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisPool
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 15:09
 */
@Setter
@Getter
@ToString
@Component
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "redis.pool")
public class RedisPool {
    private int maxIdle = 0;

    private int minIdle = 0;

    private int maxTotal = 0;

    private boolean testOnBorrow = true;
}
