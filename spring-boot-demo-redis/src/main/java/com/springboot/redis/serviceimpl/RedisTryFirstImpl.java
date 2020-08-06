package com.springboot.redis.serviceimpl;

import com.springboot.redis.service.RedisTyrFirst;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTryFirstImpl implements RedisTyrFirst {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean setKey(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public Object getKey(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o.toString();
    }

    @Override
    public void setKeyTime(String key,long keyTime) {
        redisTemplate.opsForValue().getOperations().expire(key,keyTime, TimeUnit.SECONDS);
    }
}
