package com.mzd.redistimeout.utils.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private RedisLock redisLock;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        if (expiredKey.startsWith("aaa")) {
            //在集群下会有重复监听问题
            if (redisLock.getLock(expiredKey, System.currentTimeMillis() + 3000 + "")) {
                System.out.println(expiredKey + "过期。。。");
                redisLock.unLock(expiredKey);
            }
        }
    }
}