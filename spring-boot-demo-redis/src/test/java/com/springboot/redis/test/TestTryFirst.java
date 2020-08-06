package com.springboot.redis.test;

import com.springboot.redis.StartRedisCacheApplication;
import com.springboot.redis.User;
import com.springboot.redis.service.RedisTyrFirst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartRedisCacheApplication.class})
public class TestTryFirst {
    @Resource
    private RedisTyrFirst redisTyrFirst;
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void testTryFirst() {
      /*  User user = new User(13,"ssss");
        redisTyrFirst.setKey("object",user);
       User user1 =(User) redisTyrFirst.getKey("object");
        System.out.println(user1);
        System.out.println( redisTemplate.opsForValue().setIfPresent("object","888"));
        System.out.println(redisTemplate.opsForValue().get("object"));*/
        User user = new User(13, "ssss");
        redisTemplate.opsForValue().set("object", user);
        User user1 = (User) redisTemplate.opsForValue().get("object");
        System.out.println(user1);
    }

}
