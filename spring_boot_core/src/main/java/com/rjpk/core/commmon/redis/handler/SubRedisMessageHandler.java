package com.rjpk.core.commmon.redis.handler;

import com.rjpk.core.commmon.redis.model.RedisMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName SubRedisMessage
 * @Description 订阅
 * @Author xiangnan.xu
 * @DATE 2017/12/5 17:37
 */
@Component
@Slf4j
public class SubRedisMessageHandler implements MessageListener {
    @Autowired
    private RedisTemplate redisTemplate;
    /**接收消息的方法*/
    public void onMessage(Message message, byte[] pattern){
        log.info("【redis消息监听】监听到消息开始处理，订阅频道名为：" + new String(message.getChannel()));
        byte[] body = message.getBody();
        RedisMessageVo messageVo = null;
        try {
//            String channel = new String(message.getChannel());
            messageVo = (RedisMessageVo)redisTemplate.getValueSerializer().deserialize(body);
            log.info("【redis消息监听】监听到消息处理中，订阅频道名为：" + new String(message.getChannel()) + "，处理类为：" +messageVo.getGuavaCacheEnum().getDescribe());
            //TODO 业务处理 messageVo
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("【redis消息监听】监听到消息处理结束，订阅频道名为：" + new String(message.getChannel()));
    }
}
