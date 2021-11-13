package com.biboheart.dweixin.controller;

import com.biboheart.brick.model.BhResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class DemoController {
    @RequestMapping(value = "/demo", method = { RequestMethod.GET })
    public BhResponseResult<?> demo(HttpSession session) {
        return new BhResponseResult<>(0, "success", "SUCCESS");
    }
}
