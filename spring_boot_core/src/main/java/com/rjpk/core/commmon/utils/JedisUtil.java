package com.rjpk.core.commmon.utils;

import com.mljr.common.utils.SerializationListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @ClassName JedisUtil
 * @Description Jedis工具类
 * @Author xiangnan.xu
 * @DATE 2017/11/27 15:03
 */
@Component
public class JedisUtil {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private JedisUtil() {
    }

    /**
     * 从连接池中获取Jedis
     */
    public Jedis getJedis() {
        JedisConnectionFactory jedisConnectionFactory= (JedisConnectionFactory)redisConnectionFactory;
        JedisConnection jedisConnection = (JedisConnection)jedisConnectionFactory.getConnection();
        return jedisConnection.getNativeConnection();
    }

    /**
     * 释放Jedis回连接池
     *
     * @param jedis
     */
    public void releaseJedis(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.info("jedis.close", e);
            }
        } else {
            logger.error("try to release a jedis, but jedis is null");
        }
    }
    /**
     * 设置缓存对象，并指定缓存时间
     *
     * @param key
     *            键
     * @param object
     *            要缓存的对象
     * @param seconds
     *            要缓存的时间
     */
    public void setex(String key, Object object, int seconds) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.setex(key.getBytes(), seconds, SerializationUtils.serialize(object));
        } catch (Exception e) {
            logger.error("#设置缓存对象时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
    }

    /**
     * 设置缓存对象，并指定缓存时间，返回结果 当且仅当 key 不存在，将 key 的值设为 value ，并返回1；若给定的 key 已经存在，则
     * SETNX 不做任何动作，并返回0。
     *
     * @param key
     *            键
     * @param object
     *            要缓存的对象
     * @param seconds
     *            要缓存的时间
     */
    public int setnx(String key, Object object, int seconds) {
        Long result = 0l;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            result = jedis.setnx(key.getBytes(), SerializationUtils.serialize(object));
            jedis.expire(key.getBytes(), seconds);
        } catch (Exception e) {
            logger.error("#设置缓存对象时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
        return result.intValue();
    }

    /**
     * 设置缓存对象 当且仅当 key 不存在，将 key 的值设为 value ，并返回1；若给定的 key 已经存在，则 SETNX
     * 不做任何动作，并返回0。
     *
     * @param key
     *            键
     * @param object
     *            要缓存的对象
     */
    public int setnx(String key, Object object) {
        Long result = 0l;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            result = jedis.setnx(key.getBytes(), SerializationUtils.serialize(object));
        } catch (Exception e) {
            logger.error("#设置缓存对象时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
        return result.intValue();
    }

    /**
     * 永久保存
     *
     * @param key
     * @param object
     */
    public void set(String key, Object object) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(key.getBytes(), SerializationUtils.serialize(object));
        } catch (Exception e) {
            logger.error("#设置缓存对象时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
    }

    /**
     * 获取缓存对象
     *
     * @param key
     *            键
     * @return Object
     */
    public Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            Object object = SerializationUtils.deserialize(jedis.get(key.getBytes()));
            return object;
        } catch (Exception e) {
            logger.error("#获取缓存对象时发生错误", e);
            return null;
        } finally{
            releaseJedis(jedis);
        }
    }

    /**
     * 清除缓存对象
     *
     * @param key
     *            键
     * @return Object
     */
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.del(key.getBytes());
        } catch (Exception e) {
            logger.error("#清除缓存对象时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
    }

    /**
     * 设置缓存List，并指定缓存时间
     */
    public <T> void setLs(String key, List<T> list, int seconds) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(key.getBytes(), SerializationListUtils.serialize(list));
            jedis.expire(key.getBytes(), seconds);
        } catch (Exception e) {
            logger.error("#设置缓存List时发生错误", e);
        } finally{
            releaseJedis(jedis);
        }
    }

    /**
     * 获取缓存List
     */
    public <T> List<T> getLs(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            List<T> list = SerializationListUtils.deserialize(jedis.get(key.getBytes()));
            return list;
        } catch (Exception e) {
            logger.error("#获取缓存List时发生错误", e);
            return null;
        } finally{
            releaseJedis(jedis);
        }
    }

    public Set<String> getKeys(String partner) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            Set<String> keys = jedis.keys(partner);
            return keys;
        } catch (Exception e) {
            logger.error("#获取缓存List时发生错误", e);
            return null;
        } finally{
            releaseJedis(jedis);
        }
    }
}
