package com.biboheart.dredis.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisGlobalLockImpl implements RedisGlobalLock {
    private final RedisLockRegistry redisLockRegistry;

    /* 默认30ms尝试一次, 尝试10次 */
    //private final static long LOCK_TRY_INTERVAL     = 10L;
    /** 默认尝试30s */
    private final static long LOCK_TRY_TIMEOUT      = 30 * 1000L;
    /* 单个业务持有锁的时间30s，防止死锁 */
    //private final static long LOCK_EXPIRE           = 30 * 1000L;

    @Autowired
    public RedisGlobalLockImpl(RedisLockRegistry redisLockRegistry) {
        this.redisLockRegistry = redisLockRegistry;
    }


    /**
     * 获取锁
     * @param key 锁key
     */
    @Override
    public void lock(String key) {
        log.info("开始取锁: key {}", key);
        redisLockRegistry.obtain(key).lock();
        log.info("完成取锁: key {}", key);
    }

    @Override
    public boolean tryLock(String key) {
        return tryLock(key, LOCK_TRY_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取锁
     * @param key 锁key
     * @param timeout 超时时间
     * @return 获取锁是否成功
     */
    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        log.info("开始取锁: key {}", key);
        try {
            redisLockRegistry.obtain(key).tryLock(timeout, unit);
        } catch (InterruptedException e) {
            log.info("取锁出错: key {}, error {}", key, e.getMessage());
            return false;
        }
        log.info("完成取锁: key {}", key);
        return true;
    }

    /**
     * 释放锁
     * @param key 锁key
     */
    public void unlock(String key) {
        log.info("开始解锁: key {}", key);
        try {
            redisLockRegistry.obtain(key).unlock();
        } catch (Exception e) {
            log.error("解锁失败: key {}, 错误信息: {}", key, e);
        }
        log.info("完成解锁: key {}", key);
    }
}
