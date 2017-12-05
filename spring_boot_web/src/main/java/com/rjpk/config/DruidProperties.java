package com.rjpk.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName DruidProperties
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 14:52
 */
@Getter
@Setter
@Component
@PropertySource("classpath:dataSource.properties")
@ConfigurationProperties(prefix = "jdbc")
@ToString
public class DruidProperties {
    private String url;
    private String user;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery= "SELECT 'x'";
    private boolean testWhileIdle = true;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean poolPreparedStatements = false;
}
