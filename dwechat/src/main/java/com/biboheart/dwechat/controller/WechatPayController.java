package com.biboheart.dwechat.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dwechat.service.WechatPayService;
import com.biboheart.dwechat.utils.RequestUtils;
import com.biboheart.dwechat.wechat.exception.AesException;
import com.biboheart.dwechat.wechat.exception.WxPayException;
import com.biboheart.dwechat.wechat.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WechatPayController {
    private final HttpServletRequest request;
    private final HttpSession session;
    private final WechatPayService wechatPayService;

    @Autowired
    public WechatPayController(HttpServletRequest request, HttpSession session, WechatPayService wechatPayService) {
        this.request = request;
        this.session = session;
        this.wechatPayService = wechatPayService;
    }

    @RequestMapping(value = "/public/wechat/paypage", method = RequestMethod.GET)
    public String paypage() {
        return "wechat/pay";
    }

    @RequestMapping(value = "/public/wechat/pay", method = {RequestMethod.GET, RequestMethod.POST})
    public String pay() throws WxPayException {
        // 获取请求服务的地址
        String baseUrl = RequestUtils.baseUrl(request);
        WxPayUnifiedOrderRequest wpuor = new WxPayUnifiedOrderRequest();
        wpuor.setBody("h5paydemo");
        wpuor.setOutTradeNo(TimeUtils.formatDate("yyyyMMddHHmmssSSS", null));
        wpuor.setTotalFee(1);
        wpuor.setSpbillCreateIp(RequestUtils.requestIp(request));
        wpuor.setNotifyUrl(baseUrl + "/public/wechat/payresult");
        wpuor.setTradeType("MWEB");
        WxPayUnifiedOrderResult result = wechatPayService.unifiedOrder(wpuor);
        return "redirect:" + result.getMwebUrl();
    }

    @RequestMapping(value = "/public/wechat/paymy", method = {RequestMethod.GET, RequestMethod.POST})
    public String payMy(UnifiedOrderParams uop) throws AesException {
        // 获取请求服务的地址
        String baseUrl = RequestUtils.baseUrl(request);
        if (null == uop) {
            uop = new UnifiedOrderParams();
        }
        uop.setBody("h5paydemo");
        uop.setOutTradeNo(TimeUtils.formatDate("yyyyMMddHHmmssSSS", null));
        uop.setTotalFee(1);
        uop.setSpbillCreateIp(RequestUtils.requestIp(request));
        uop.setNotifyUrl(baseUrl + "/public/wechat/payresult");
        uop.setTradeType("MWEB");
        PayResult result = wechatPayService.pay(uop);
        return "redirect:" + result.getMwebUrl();
    }

    @RequestMapping(value = "/public/wechat/payresult", method = RequestMethod.POST)
    @ResponseBody
    public String receivePayResult(@RequestBody String message) {
        System.out.println(this.getClass().getName() + "---message:" + message);
        return "success";
    }

    @RequestMapping(value = "/public/wechat/jspay", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WechatJsPayParams jspay() throws WxPayException, AesException {
        // 获取请求服务的地址
        String baseUrl = RequestUtils.baseUrl(request);
        String openid = session.getAttribute("openid").toString();
        WxPayUnifiedOrderRequest wpuor = new WxPayUnifiedOrderRequest();
        wpuor.setBody("JSAPIpaydemo");
        wpuor.setOutTradeNo(TimeUtils.formatDate("yyyyMMddHHmmssSSS", null));
        wpuor.setTotalFee(1);
        wpuor.setSpbillCreateIp(RequestUtils.requestIp(request));
        wpuor.setNotifyUrl(baseUrl + "/public/wechat/payresult");
        wpuor.setTradeType("JSAPI");
        wpuor.setOpenid(openid);
        WxPayUnifiedOrderResult result = wechatPayService.unifiedOrder(wpuor);
        return wechatPayService.generateJsParams(result);
        /*WxPayUnifiedOrderResult result;
        try {
            result = wechatPayService.unifiedOrder(wpuor);
        } catch (WxPayException e) {
            System.out.println(e.hashCode() + ':' + e.getReturnMsg());
            return new BhResponseResult<>(e.hashCode(), "支付下单出错:" + e.getReturnMsg(), null);
        }
        WechatJsPayParams params;
        try {
            params = wechatPayService.generateJsParams(result);
        } catch (AesException e) {
            return new BhResponseResult<>(e.hashCode(), "支付参数出错:" + e.getMessage(), null);
        }
        return new BhResponseResult<>(0, "success", params);*/
    }
}
