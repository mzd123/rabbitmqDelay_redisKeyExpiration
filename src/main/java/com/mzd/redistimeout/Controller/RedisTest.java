package com.mzd.redistimeout.Controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisTest {


    @Resource
    private RedisTemplate redisTemplate;

    //给redis中赋值
    @RequestMapping("/redis")
    public String redis() {
        redisTemplate.opsForValue().set("aaa", "123", 30, TimeUnit.SECONDS);
        return "success";
    }


}
