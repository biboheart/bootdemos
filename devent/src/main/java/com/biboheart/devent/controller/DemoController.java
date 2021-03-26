package com.biboheart.devent.controller;

import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.devent.events.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {
    private final ApplicationContext applicationContext;

    @Autowired
    public DemoController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String test() {
        log.info("run test");
        applicationContext.publishEvent(new DemoEvent("text", "text"));
        log.info("2 {} publish end", TimeUtils.formatDate(null, null));
        return "test";
    }
}
