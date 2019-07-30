package com.mzd.redistimeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

@SpringBootApplication
public class RedistimeoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistimeoutApplication.class, args);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer(Charset.forName("UTF8")));
        redisTemplate.setValueSerializer(new StringRedisSerializer(Charset.forName("UTF8")));
        return redisTemplate;
    }


}
