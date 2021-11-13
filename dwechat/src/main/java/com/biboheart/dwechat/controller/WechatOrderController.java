package com.biboheart.dwechat.controller;

import com.biboheart.brick.model.BhResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 向微信下单
 */
@RestController
@RequestMapping(value = "/wechat/order")
public class WechatOrderController {
    /**
     * 向微信支付平台下单
     * @return
     */
    @RequestMapping(value = "/apply", method = {RequestMethod.GET, RequestMethod.POST})
    public BhResponseResult<?> apply() {
        return new BhResponseResult<>(0, "success", true);
    }
}
