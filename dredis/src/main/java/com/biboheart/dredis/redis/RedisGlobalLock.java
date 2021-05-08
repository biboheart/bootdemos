package com.biboheart.dredis.redis;

import java.util.concurrent.TimeUnit;

public interface RedisGlobalLock {
    /**
     * 获取锁
     * @param key        锁Key
     */
    void lock(String key);

    /**
     * 尝试获取锁
     * @param key     锁Key
     * @return       是否获取锁
     */
    boolean tryLock(String key);

    /**
     * 获取锁
     * @param key        锁Key
     * @param timeout      有效期
     * @param unit   有效期时间单位
     * @return          是否获取锁
     */
    boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 释放锁
     * @param key  锁Key
     */
    void unlock(String key);
}
