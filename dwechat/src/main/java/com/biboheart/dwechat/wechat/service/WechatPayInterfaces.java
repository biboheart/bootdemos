package com.biboheart.dwechat.wechat.service;

import com.biboheart.brick.utils.BeanUtils;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.StringUtils;
import com.biboheart.brick.utils.TimeUtils;
import com.biboheart.dwechat.wechat.exception.AesException;
import com.biboheart.dwechat.wechat.exception.WxPayException;
import com.biboheart.dwechat.wechat.model.*;
import com.biboheart.dwechat.wechat.utils.WechatEncryptionUtils;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WechatPayInterfaces {
    private final RestTemplate restTemplate;
    private final XStream payResultXstream;
    private final XStream queryOrderResult;

    private final static String WeixinPayUri = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private final static String WeixinPayQueryUri = "https://api.mch.weixin.qq.com/pay/orderquery";

    @Value("${wechat.pay.key:null}")
    private String key;
    @Value("${wechat.pay.appid:null}")
    private String appid;
    @Value("${wechat.pay.mchid:null}")
    private String mchid;
    @Value("${wechat.pay.keyPath:null}")
    private String keyPath;

    @Autowired
    public WechatPayInterfaces(RestTemplate restTemplate, XStream payResultXstream, XStream queryOrderResult) {
        this.restTemplate = restTemplate;
        this.payResultXstream = payResultXstream;
        this.queryOrderResult = queryOrderResult;
    }

    public PayResult pay(UnifiedOrderParams uop) throws AesException {
        uop.setNonceStr(WechatEncryptionUtils.getRandomStr(32));
        if (CheckUtils.isEmpty(uop.getAppid())) {
            uop.setAppid(appid);
        }
        if (CheckUtils.isEmpty(uop.getMchId())) {
            uop.setMchId(mchid);
        }
        if (!CheckUtils.isEmpty(uop.getDetail()) && uop.getDetail().indexOf("<![CDATA[") != 0) {
            uop.setDetail("<![CDATA[" + uop.getDetail() + "]]");
        }
        Map<String, Object> temp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        BeanUtils.beanToMap(uop, temp, null, null);
        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            String underlineKey = StringUtils.camelToUnderline(entry.getKey());
            data.put(underlineKey, entry.getValue());
        }
        String sign = WechatEncryptionUtils.sign(data, key);
        uop.setSign(sign);
        data.put("sign", sign);
        String body = WechatEncryptionUtils.generatePayData(data);
        String result = restTemplate.postForObject(WeixinPayUri, body, String.class);
        System.out.println(this.getClass().getName() + "---pay result:" + result);
        if (null == result) {
            return null;
        }
        return (PayResult) payResultXstream.fromXML(result);
    }

    public WechatJsPayParams generateJsParams(PayResult payResult) throws AesException {
        WechatJsPayParams jp = new WechatJsPayParams();
        if (CheckUtils.isEmpty(payResult.getAppid())) {
            jp.setAppid(appid);
        } else {
            jp.setAppid(payResult.getAppid());
        }
        jp.setTimeStamp(String.valueOf(TimeUtils.getCurrentTimeInMillis() / 1000));
        jp.setNonceStr(WechatEncryptionUtils.getRandomStr(32));
        jp.setPrepayId("prepay_id=" + payResult.getPrepayId());
        jp.setSignType("MD5");
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> temp = new HashMap<>();
        BeanUtils.beanToMap(jp, temp, null, null);
        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            String key = entry.getKey();
            if ("appid".equals(key)) {
                key = "appId";
            }
            if ("prepayId".equals(key)) {
                key = "package";
            }
            data.put(key, entry.getValue());
        }
        String sign = WechatEncryptionUtils.sign(data, key);
        jp.setPaySign(sign);
        return jp;
    }

    public WechatJsPayParams generateJsParams(WxPayUnifiedOrderResult payResult) throws AesException {
        WechatJsPayParams jp = new WechatJsPayParams();
        if (CheckUtils.isEmpty(payResult.getAppid())) {
            jp.setAppid(appid);
        } else {
            jp.setAppid(payResult.getAppid());
        }
        jp.setTimeStamp(String.valueOf(TimeUtils.getCurrentTimeInMillis() / 1000));
        jp.setNonceStr(WechatEncryptionUtils.getRandomStr(32));
        jp.setPrepayId("prepay_id=" + payResult.getPrepayId());
        jp.setSignType("MD5");
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> temp = new HashMap<>();
        BeanUtils.beanToMap(jp, temp, null, null);
        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            String key = entry.getKey();
            if ("appid".equals(key)) {
                key = "appId";
            }
            if ("prepayId".equals(key)) {
                key = "package";
            }
            data.put(key, entry.getValue());
        }
        String sign = WechatEncryptionUtils.sign(data, key);
        jp.setPaySign(sign);
        return jp;
    }

    public QueryOrderResult queryOrder(QueryOrderParams qop) throws AesException {
        if (CheckUtils.isEmpty(qop.getAppid())) {
            qop.setAppid(appid);
        }
        if (CheckUtils.isEmpty(qop.getMchId())) {
            qop.setMchId(mchid);
        }
        Map<String, Object> temp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        BeanUtils.beanToMap(qop, temp, null, null);
        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            String underlineKey = StringUtils.camelToUnderline(entry.getKey());
            data.put(underlineKey, entry.getValue());
        }
        String sign = WechatEncryptionUtils.sign(data, key);
        qop.setSign(sign);
        data.put("sign", sign);
        String body = WechatEncryptionUtils.generatePayData(data);
        String result = restTemplate.postForObject(WeixinPayQueryUri, body, String.class);
        System.out.println(this.getClass().getName() + "---query result:" + result);
        if (null == result) {
            return null;
        }
        return (QueryOrderResult) queryOrderResult.fromXML(result);
    }

    public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appid);
        payConfig.setMchId(mchid);
        payConfig.setMchKey(key);
        payConfig.setKeyPath(keyPath);
        request.checkAndSign(payConfig);

        String responseContent = restTemplate.postForObject(WeixinPayUri, request.toXML(), String.class);
        WxPayUnifiedOrderResult result = BaseWxPayResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
        result.checkResult(key, request.getSignType(), true);
        return result;
    }
}
