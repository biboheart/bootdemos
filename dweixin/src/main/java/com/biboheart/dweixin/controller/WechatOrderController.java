package com.biboheart.dweixin.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dweixin.pay.model.OrderRequest;
import com.biboheart.dweixin.service.WechatPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/order")
public class WechatOrderController {
    private final WechatPayService wechatPayService;
    private final HttpSession session;
    private final RestTemplate restTemplate;

    public WechatOrderController(WechatPayService wechatPayService, HttpSession session, RestTemplate restTemplate) {
        this.wechatPayService = wechatPayService;
        this.session = session;
        this.restTemplate = restTemplate;
    }

    /**
     * 向微信支付平台下单
     */
    @RequestMapping(value = "/apply", method = {RequestMethod.POST})
    public BhResponseResult<?> apply(OrderRequest order) {
        log.info("用户{}申请支付:", session.getAttribute("openid"));
        String body = wechatPayService.apply(order);
        log.info("支付入参: {}", body);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> request = new HttpEntity<>(body, headers);
//        String result = restTemplate.postForObject("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi", body, String.class);
        ResponseEntity<String> result = restTemplate.exchange("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi", HttpMethod.POST, request, String.class);
        return new BhResponseResult<>(0, "success", result);
    }

    /**
     * 接收支付结果通知
     */
    @RequestMapping(value = "/notify", method = {RequestMethod.POST})
    public void notify(@RequestBody String message) {
        log.info("接收到支付结果: {}", message);
    }
}
