package com.biboheart.dwxpay.domain.impl;

import com.biboheart.dwxpay.wechat.IWXPayDomain;
import com.biboheart.dwxpay.wechat.WXPayConfig;
import com.biboheart.dwxpay.wechat.WXPayConstants;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class EberWxpayConfig extends WXPayConfig {
    private final String appid;
    private final String mch_id;
    private final String mch_key;

    private final byte[] certData;

    public EberWxpayConfig(String appid, String mch_id, String mch_key, String certPath) throws Exception {
        this.appid = appid;
        this.mch_id = mch_id;
        this.mch_key = mch_key;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return this.appid;
    }

    @Override
    public String getMchID() {
        return this.mch_id;
    }

    @Override
    public String getKey() {
        return this.mch_key;
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, false);
            }
        };
    }
}
