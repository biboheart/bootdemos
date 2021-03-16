package com.biboheart.dwechat.controller;

import com.biboheart.dwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String home(HttpSession session) {
        SessionUtils.showSession(session, "home");
        /*String openid = session.getAttribute("openid").toString();
        log.info("openid:" + openid + "---进入首页");*/
        return "index";
    }
}
