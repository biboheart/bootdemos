package com.biboheart.dweixin.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dweixin.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/mp")
public class WechatController {
    private final WechatService wechatService;

    public WechatController(WechatService wechatService) {
        this.wechatService = wechatService;
    }

    @RequestMapping(value = "/token", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> accessToken() {
        return new BhResponseResult<>(0, "success", wechatService.accessToken());
    }
}
