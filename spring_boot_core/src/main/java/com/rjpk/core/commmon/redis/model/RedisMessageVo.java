package com.rjpk.core.commmon.redis.model;

import com.mljr.common.enums.GuavaCacheEnum;

import java.io.Serializable;

/**
 * @ClassName SmsMessageVo
 * @Description 消息对象
 * @Author xiangnan.xu
 * @DATE 2017/11/25 22:10
 */
public class RedisMessageVo<K> implements Serializable{
    private K key;
    private GuavaCacheEnum guavaCacheEnum;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public GuavaCacheEnum getGuavaCacheEnum() {
        return guavaCacheEnum;
    }

    public void setGuavaCacheEnum(GuavaCacheEnum guavaCacheEnum) {
        this.guavaCacheEnum = guavaCacheEnum;
    }
}
