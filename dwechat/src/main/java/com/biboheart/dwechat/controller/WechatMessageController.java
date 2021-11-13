package com.biboheart.dwechat.controller;

import com.biboheart.dwechat.service.WechatService;
import com.biboheart.dwechat.wechat.model.WechatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/message")
public class WechatMessageController {
    private final WechatService wechatService;

    public WechatMessageController(WechatService wechatService) {
        this.wechatService = wechatService;
    }

    /**
     * 用于接收微信公众号消息，在公众号中配置url地址时需要接收消息，验证后返回
     */
    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public String receive(String signature, String timestamp, String nonce, String echostr) {
        System.out.println(this.getClass().getName() + "---signature:" + signature + ";timestamp:" + timestamp + ";nonce:" + nonce + ";echostr:" + echostr);
        if (wechatService.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    /**
     * 用于接收微信公众号消息，用户触发的消息，如关注、扫码等
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String receive(@RequestBody String message) {
        log.info("接收微信消息: {}", message);
        WechatMessage wechatMessage = WechatMessage.fromXML(message);
        return wechatService.processMessage(wechatMessage);
    }
}
