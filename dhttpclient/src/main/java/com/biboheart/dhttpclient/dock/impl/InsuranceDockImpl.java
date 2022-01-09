package com.biboheart.dhttpclient.dock.impl;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dhttpclient.dock.InsuranceDock;
import com.biboheart.dhttpclient.dock.mode.DiffCodeEnum;
import com.biboheart.dhttpclient.support.BhHttpBody;
import com.biboheart.dhttpclient.support.HttpUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class InsuranceDockImpl implements InsuranceDock {
    // 选择实现通道 1: 本地医保, 2: 异地医保
    private final Integer route = 2;
    private final String url = "http://192.168.2.155:10231";

    @Override
    public Integer getRoute() {
        return route;
    }

    @Override
    public String request(RestTemplate restTemplate, String code, String data1, String data2, String data3) throws BhException {
        if (null == restTemplate) {
            throw new BhException("RestTemplate对象实例不能为空");
        }
        if (CheckUtils.isEmpty(code)) {
            throw new BhException("code不能为空");
        }
        Integer nCode = Integer.valueOf(code);
        DiffCodeEnum diffCode = DiffCodeEnum.getDiffCode(nCode);
        if (null == diffCode) {
            throw new BhException("code值不正确");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("route", route);
        params.put("InstCode", "330799108331");
        params.put("TransCode", nCode);
        params.put("data1", data1);
        params.put("data2", getData2(restTemplate, diffCode));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BhHttpBody body = HttpUtils.request(restTemplate, url, HttpMethod.POST, headers, params, BhHttpBody.class);
        if (null == body) {
            throw new BhException("调用交易失败");
        }
        return HttpUtils.getResult(body.getResult(), String.class);
    }

    @Override
    public String request(RestTemplate restTemplate, String code, String data1) throws BhException {
        return request(restTemplate, code, data1, null, null);
    }

    // 根据文档说明，获取data2数据
    private String getData2(RestTemplate restTemplate, DiffCodeEnum diffCode) throws BhException {
        if (null == diffCode) {
            return null;
        }
        String data2 = "330000";
        if (Integer.valueOf(1).equals(diffCode.getType())) {
            String card = readCard(restTemplate);
            if (CheckUtils.isEmpty(card)) {
                throw new BhException("未读取到医保卡信息");
            }
            String[] cardArr = card.split("\\|");
            data2 = cardArr[0];
        }
        return data2;
    }

    // 读取卡片信息，未产生交易，不能取医保信息
    @Override
    public String readCard(RestTemplate restTemplate) throws BhException {
        Map<String, Object> params = new HashMap<>();
        params.put("route", 8);
        params.put("InstCode", "330799108331");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BhHttpBody body = HttpUtils.request(restTemplate, url, HttpMethod.POST, headers, params, BhHttpBody.class);
        if (null == body) {
            throw new BhException("读卡失败");
        }
        return HttpUtils.getResult(body.getResult(), String.class);
    }
}
