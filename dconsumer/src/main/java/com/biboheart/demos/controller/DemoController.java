package com.biboheart.demos.controller;

import com.biboheart.brick.model.BhResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class DemoController {

    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    public BhResponseResult<?> test() {
        return new BhResponseResult<>(0, "success", true);
    }
}
