package com.biboheart.dweixin.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WechatPayInterfaces {
    private final RestTemplate restTemplate;

    public WechatPayInterfaces(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
