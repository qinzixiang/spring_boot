package com.rjpk.web.test;

import com.mljr.common.enums.GuavaCacheEnum;
import com.rjpk.config.RedisConfig;
import com.rjpk.config.RedisProperties;
import com.rjpk.core.commmon.redis.handler.PubMessageHandler;
import com.rjpk.core.commmon.utils.JedisUtil;
import com.rjpk.core.test.model.generated.Book;
import com.rjpk.core.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName DbController
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 10:07
 */
@RestController
public class DbController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    BookService bookService;
    @Autowired
    RedisProperties redisProperties;
    @Autowired
    PubMessageHandler pubMessageHandler;

    @RequestMapping("/getBooks")
    public List<Map<String, Object>> getBooks(){
        String sql = "select * from book";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet( );
            if(entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator( );
                while(iterator.hasNext( )) {
                    Map.Entry<String, Object> entry =(Map.Entry<String, Object>) iterator.next( );
                    Object key = entry.getKey( );
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/getBook")
    public Object getBook(){
//        Book book = bookService.getBook();
//        JedisConnectionFactory jedisConnectionFactory= (JedisConnectionFactory)redisConnectionFactory;
//        Jedis resource = jedisConnectionFactory.getShardInfo().createResource();
//        resource.set("test","222222222");
        pubMessageHandler.sendMessage(GuavaCacheEnum.ACCESSRULECONFIGURATIONCACHE,"wwwwwwwwwwwwwwwww");
        return "success";
    }
}
