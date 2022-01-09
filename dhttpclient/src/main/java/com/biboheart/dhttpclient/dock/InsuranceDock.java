package com.biboheart.dhttpclient.dock;

import com.biboheart.brick.exception.BhException;
import org.springframework.web.client.RestTemplate;

public interface InsuranceDock {
    Integer getRoute();
    String request(RestTemplate restTemplate, String code, String data1, String data2, String data3) throws BhException;
    String request(RestTemplate restTemplate, String code, String data1) throws BhException;
    String readCard(RestTemplate restTemplate) throws BhException;
}
