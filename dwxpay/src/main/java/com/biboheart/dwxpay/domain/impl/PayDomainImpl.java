package com.biboheart.dwxpay.domain.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.dwxpay.domain.PayDomain;
import com.biboheart.dwxpay.wechat.WXPay;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayDomainImpl implements PayDomain {
    private final String certPath = "E:\\test\\cert\\apiclient_cert.p12";
    private final String appid = "wx2e7673a1ec1d476c";
    private final String mchId = "1630764420";
    private final String localDomain = "https://dev.21ber.cn/";
    private String key = "Hqb4U4JWimpPkTQdgPptoCWfh6vLmpND";
    //private String key = "b0cf44b97af365c340d9c1d12f793a5d";

    @Override
    public Object order() throws BhException {
        try {
            EberWxpayConfig config = new EberWxpayConfig(appid, mchId, key, certPath);
            WXPay wxpay = new WXPay(config, false, false);
            Map<String, String> data = new HashMap<>();
            data.put("body", "试试");
            data.put("sub_mch_id", "1632343070");
            data.put("out_trade_no", "2201020000103");
            data.put("total_fee", String.valueOf(10));
            data.put("spbill_create_ip", "192.168.2.155");
            data.put("auth_code", "132741667423051512");
            Map<String, String> resp = wxpay.microPay(data);
            System.out.println(resp);
        } catch (Exception e) {
            throw new BhException(e.getMessage());
        }
        return null;
    }

    @Override
    public Object query(String outTradeNo) throws BhException {
        return null;
    }

    @Override
    public Object reverse(String outTradeNo) throws BhException {
        return null;
    }

    @Override
    public Object refund(String outTradeNo, String sourceOutTradeNo, Long refundAmount, String notifyUrl) throws BhException {
        return null;
    }

    @Override
    public Object refundQuery(String outTradeNo, String sourceOutTradeNo) throws BhException {
        return null;
    }
}
