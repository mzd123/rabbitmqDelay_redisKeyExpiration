package com.mzd.redistimeout.utils.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisLock {

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 获取锁
     */
    //默认为5秒钟超时
    private String timeOut = System.currentTimeMillis() + 1000 * 5 + "";

    public boolean getLock(String key, String value) {

        if (key == null || key.equals("")) {
            return false;
        }

        if (value == null || value.equals("")) {
            value = timeOut;
        }

        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        Object oldValue = redisTemplate.opsForValue().get(key);

        if (oldValue == null) {
            return true;
        }

        //说明超时了
        if (Long.valueOf(oldValue.toString()) - System.currentTimeMillis() < 0) {

            Object currentVaule = redisTemplate.opsForValue().getAndSet(key, value);

            if (currentVaule != null && currentVaule.toString().equals(oldValue)) {
                return true;
            }

        }

        return false;
    }


    /**
     * 解锁
     */
    public void unLock(String key) {
        if (key != null) {
            redisTemplate.delete(key);
        }
    }

}
