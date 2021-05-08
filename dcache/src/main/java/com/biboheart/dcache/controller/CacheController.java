package com.biboheart.dcache.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dcache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CacheController {
    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    public BhResponseResult<?> test(String name) {
        return new BhResponseResult<>(0, "success", cacheService.load(name));
    }
}
