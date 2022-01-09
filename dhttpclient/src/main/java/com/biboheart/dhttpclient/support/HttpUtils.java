package com.biboheart.dhttpclient.support;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.brick.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtils {
    @SuppressWarnings("unchecked")
    public static <T> T getResult (Object data, Class<T> valueType) {
        if (null == data) {
            return null;
        }
        String str = (data instanceof String) ? (String) data : JsonUtils.obj2json(data);
        if (null == str || "".equals(str)) {
            return null;
        }
        if (String.class.equals(valueType)) {
            return (T) str;
        }
        return (T) JsonUtils.json2obj(str, valueType);
    }

    public static <T> T getBhResult(String body, Class<T> valueType) {
        if (null == body || "".equals(body)) {
            return null;
        }
        BhHttpBody httpBody = (BhHttpBody) JsonUtils.json2obj(body, BhHttpBody.class);
        return getResult(httpBody.getResult(), valueType);
    }

    @SuppressWarnings("unchecked")
    public static HttpHeaders packageHeaders(String headers) {
        HttpHeaders headerObj = new HttpHeaders();
        if (CheckUtils.isEmpty(headers)) {
            headerObj.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }
        Map<String, String> headerMap = (Map<String, String>) JsonUtils.json2obj(headers, Map.class);
        if (CheckUtils.isEmpty(headerMap)) {
            headerObj.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        } else {
            headerMap.forEach(headerObj::add);
        }
        return headerObj;
    }

    public static <T> T request(RestTemplate restTemplate,
                                String url,
                                HttpMethod method,
                                HttpHeaders headers,
                                Object params,
                                Class<T> valueType) throws BhException {
        if (null == headers) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }
        HttpEntity<?> request;
        log.info("请求[{}],headers:[{}]", url, headers);
        if (method.equals(HttpMethod.POST)) {
            if (MediaType.APPLICATION_FORM_URLENCODED.includes(headers.getContentType())) {
                if (!(params instanceof Map)) {
                    throw new BhException("请使用" + MediaType.APPLICATION_JSON_VALUE + "的Content_type");
                }
                MultiValueMap<String, Object> postParams = postBody(params);
                log.info("请求[{}],参数:{}", url, null == postParams ? "null" : postParams.toString());
                request = new HttpEntity<>(postParams, headers);
            } else {
                String inParams = JsonUtils.obj2json(params);
                log.info("请求[{}],参数:{}", url, null == inParams ? "null" : inParams);
                request = new HttpEntity<>(inParams, headers);
            }
        } else {
            String getParams = getParams(params);
            if (!CheckUtils.isEmpty(getParams)) {
                url += "?" + getParams;
            }
            log.info("请求[" + url + "],参数:[" + (null == getParams ? "" : getParams) + "]");
            request = new HttpEntity<>(null, headers);
        }
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, method, request, String.class);
        } catch (RestClientException e) {
            log.error("请求[" + url + "],出错:" + e.getMessage());
            throw new BhException(e.getMessage());
        }
        if (response.getStatusCodeValue() != 200) {
            throw new BhException(response.getBody());
        }
        String body = response.getBody();
        if (CheckUtils.isEmpty(body)) {
            log.info("请求[" + url + "],结果为空");
            return null;
        }
        log.info("请求[" + url + "],结果:[" + body + "]");
        return HttpUtils.getResult(body, valueType);
    }

    @SuppressWarnings("unchecked")
    private static String getParams(Object params) {
        if (null == params) {
            return null;
        }
        if (!(params instanceof Map)) {
            return null;
        }
        Map<String, Object> paramsMap = (Map<String, Object>) params;
        List<String> paramList = new ArrayList<>();
        paramsMap.forEach((k, v) -> {
            if (null == v) {
                return;
            }
            paramList.add(k + "=" + v);
        });
        if (CheckUtils.isEmpty(paramList)) {
            return null;
        }
        return String.join("&", paramList);
    }

    @SuppressWarnings("unchecked")
    private static MultiValueMap<String, Object> postBody(Object params) {
        if (null == params) {
            return null;
        }
        if (!(params instanceof Map)) {
            return null;
        }
        Map<String, Object> paramsMap = (Map<String, Object>) params;
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
        paramsMap.forEach((k, v) -> {
            if (null == v) {
                return;
            }
            if (v instanceof List) {
                List<Map<String, Object>> datas = (List<Map<String, Object>>) v;
                int i = 0;
                for (Map<String, Object> data : datas) {
                    int finalI = i;
                    data.forEach((dk, dv) -> result.add(k + "[" + finalI + "]." + dk, dv));
                    i ++;
                }
                return;
            } else if (v instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) v;
                data.forEach((dk, dv) -> result.add(k + "." + dk, dv));
                return;
            }
            result.add(k, v);
        });
        return result;
    }
}
