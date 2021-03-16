package com.biboheart.dhelloworld.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${custom.name:default}")
    private String customName;

    @RequestMapping(value = "/hello")
    public String hello(String name) {
        System.out.println("配置文件自定义配置的值是: " + customName);
        return "hello " + name + " with " + customName;
    }
}
