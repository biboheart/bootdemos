package com.biboheart.dcache.service.impl;

import com.biboheart.brick.utils.JsonUtils;
import com.biboheart.dcache.model.User;
import com.biboheart.dcache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {
    private static Integer id = 0;

    @Override
    public User load(String name) {
        log.info("start load:{}", name);
        User user = new User(id++, name, 36);
        log.info("return load:{}", JsonUtils.obj2json(user));
        return user;
    }
}
