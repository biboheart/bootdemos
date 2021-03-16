package com.biboheart.dhttpclient.controller;

import com.biboheart.dhttpclient.events.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final ApplicationContext applicationContext;

    @Autowired
    public ApiController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello() {
        applicationContext.publishEvent(new DemoEvent("demo"));
        return "api hello";

    }
}
