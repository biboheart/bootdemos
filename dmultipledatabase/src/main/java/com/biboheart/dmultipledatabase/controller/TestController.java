package com.biboheart.dmultipledatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dmultipledatabase.entity.Test;
import com.biboheart.dmultipledatabase.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/demo/test/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(Integer id) {
        Test test = testService.load(id);
        return new BhResponseResult<>(0, "success", test);
    }
}
