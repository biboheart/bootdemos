package com.biboheart.dredis.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dredis.model.User;
import com.biboheart.dredis.redis.RedisGlobalLock;
import com.biboheart.dredis.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RedisController {
    private final RedisUtils redisUtils;
    private final RedisGlobalLock redisGlobalLock;

    public RedisController(RedisUtils redisUtils, RedisGlobalLock redisGlobalLock) {
        this.redisUtils = redisUtils;
        this.redisGlobalLock = redisGlobalLock;
    }

    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    public BhResponseResult<?> test(String name) {
        Long time = (TimeUtils.getDaysZeroMillis(0, 1) - TimeUtils.getCurrentTimeInMillis()) / 1000;
        System.out.println(time);
        redisUtils.hset("demo:cache", name, new User(1, "bibo", "123"), 60);
        System.out.println(redisUtils.hget("demo:cache", name));
        return new BhResponseResult<>(0, "success", null);
    }

    @RequestMapping(value = "/lock", method = {RequestMethod.GET})
    public BhResponseResult<?> lock() {
        String lockKey = "com.biboheart.dredis.controller,lock,demo";
        boolean success = redisGlobalLock.tryLock(lockKey);
        System.out.println(success);
        /*if (!redisGlobalLock.tryLock(lockKey)) {
            redisGlobalLock.unlock(lockKey);
        }*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisGlobalLock.unlock(lockKey);
        return new BhResponseResult<>(0, "success", null);
    }
}
