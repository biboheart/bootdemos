package com.biboheart.dcache.service;

import com.biboheart.dcache.model.User;
import org.springframework.cache.annotation.Cacheable;

public interface CacheService {
    @Cacheable(cacheNames={"user"})
    User load(String name);
}
