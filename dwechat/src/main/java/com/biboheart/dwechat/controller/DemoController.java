package com.biboheart.dwechat.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class DemoController {
    @RequestMapping(value = "/demo", method = { RequestMethod.GET })
    public BhResponseResult<?> demo(HttpSession session) {
        SessionUtils.showSession(session, "demo");
        return new BhResponseResult<>(0, "操作成功", "SUCCESS");
    }
}
