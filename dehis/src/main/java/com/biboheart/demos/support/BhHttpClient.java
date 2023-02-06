package com.biboheart.demos.support;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class BhHttpClient {

    public static <T> T request(RestTemplate restTemplate,
                                String method,
                                String url,
                                HttpHeaders headers,
                                Map<String, Object> params,
                                Map<String, Object> refer,
                                Class<T> valueType) throws BhException {
        if (null == headers) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
        Map<String, Object> paramsMap = convertParams(params, refer);
        log.info(JsonUtils.obj2json(paramsMap));
        HttpEntity<MultiValueMap<String, Object>> request;
        if ("POST".equalsIgnoreCase(method)) {
            MultiValueMap<String, Object> postParams = postBody(paramsMap);
            log.info("请求[" + url + "],参数:[" + (null == postParams ? "" : postParams.toString()) + "]");
            request = new HttpEntity<>(postParams, headers);
        } else {
            String getParams = getParams(paramsMap);
            if (!CheckUtils.isEmpty(getParams)) {
                url += "?" + getParams;
            }
            log.info("请求[" + url + "],参数:[" + (null == getParams ? "" : getParams) + "]");
            request = new HttpEntity<>(null, headers);
        }
        ResponseEntity<BhHttpBody> response;
        try {
            response = restTemplate.exchange(url, "POST".equalsIgnoreCase(method) ? HttpMethod.POST : HttpMethod.GET, request, BhHttpBody.class);
        } catch (RestClientException e) {
            log.error("请求[" + url + "],出错:" + e.getMessage());
            throw new BhException(e.getMessage());
        }
        BhHttpBody body = response.getBody();
        if (null == body) {
            log.info("请求[" + url + "],结果为空");
            return null;
        }
        log.info("请求[" + url + "],结果:{}", body);
        if (!body.getCode().equals(0) && !body.getCode().equals(200)) {
            throw new BhException(body.getMessage());
        }
        return HttpUtils.getResult(body.getResult(), valueType);
    }

    public static <T> T request(RestTemplate restTemplate,
                                String method,
                                String url,
                                HttpHeaders headers,
                                Object params,
                                Class<T> valueType) throws BhException {
        if (null == headers) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }
        HttpEntity<?> request;
        if ("POST".equalsIgnoreCase(method)) {
            if (MediaType.APPLICATION_FORM_URLENCODED.includes(headers.getContentType())) {
                if (!(params instanceof Map)) {
                    throw new BhException("请使用" + MediaType.APPLICATION_JSON_VALUE + "的Content_type");
                }
                MultiValueMap<String, Object> postParams = postBody(params);
                log.info("请求[{}],参数:{}", url, null == postParams ? "null" : postParams.toString());
                request = new HttpEntity<>(postParams, headers);
            } else {
                String inParams = (params instanceof String) ? (String) params : JsonUtils.obj2json(params);
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
        ResponseEntity<BhHttpBody> response;
        try {
            response = restTemplate.exchange(url, "POST".equalsIgnoreCase(method) ? HttpMethod.POST : HttpMethod.GET, request, BhHttpBody.class);
        } catch (RestClientException e) {
            log.error("请求[" + url + "],出错:" + e.getMessage());
            throw new BhException(e.getMessage());
        }
        BhHttpBody body = response.getBody();
        if (null == body) {
            log.info("请求[" + url + "],结果为空");
            return null;
        }
        log.info("请求[" + url + "],结果:{}", body);
        if (!body.getCode().equals(0) && !body.getCode().equals(200)) {
            throw new BhException(body.getMessage());
        }
        return HttpUtils.getResult(body.getResult(), valueType);
    }

    @SuppressWarnings("unchecked")
    public static <T> T requestAny(RestTemplate restTemplate,
                                String method,
                                String url,
                                HttpHeaders headers,
                                Object params,
                                Class<T> valueType) throws BhException {
        if (null == headers) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }
        HttpEntity<?> request;
        if ("POST".equalsIgnoreCase(method)) {
            if (MediaType.APPLICATION_FORM_URLENCODED.includes(headers.getContentType())) {
                if (!(params instanceof Map)) {
                    throw new BhException("请使用" + MediaType.APPLICATION_JSON_VALUE + "的Content_type");
                }
                MultiValueMap<String, Object> postParams = postBody(params);
                log.info("请求[{}],参数:{}", url, null == postParams ? "null" : postParams.toString());
                request = new HttpEntity<>(postParams, headers);
            } else {
                String inParams = (params instanceof String) ? (String) params : JsonUtils.obj2json(params);
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
            response = restTemplate.exchange(url, "POST".equalsIgnoreCase(method) ? HttpMethod.POST : HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            log.error("请求[" + url + "],出错:" + e.getMessage());
            throw new BhException(e.getMessage());
        }
        String body = response.getBody();
        if (null == body) {
            log.info("请求[" + url + "],结果为空");
            return null;
        }
        if (valueType.equals(String.class)) {
            return (T) body;
        }
        return (T) JsonUtils.json2obj(body, valueType);
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
            result.add(k, v);
        });
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> requestToken(RestTemplate restTemplate,
                                                   String url,
                                                   HttpHeaders headers,
                                                   Map<String, Object> params) throws BhException {
        if (null == headers) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
        HttpEntity<MultiValueMap<String, Object>> request;
        MultiValueMap<String, Object> postParams = postBody(params);
        log.info("请求[" + url + "],参数:[" + (null == postParams ? "" : postParams.toString()) + "]");
        request = new HttpEntity<>(postParams, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if (HttpStatus.OK != response.getStatusCode()) {
            throw new BhException("请求[" + url + "]出错:" + response.getStatusCodeValue());
        }
        String body = response.getBody();
        if (CheckUtils.isEmpty(body)) {
            log.info("请求[" + url + "],结果为空");
            return null;
        }
        return (Map<String, Object>) JsonUtils.json2obj(body, Map.class);
    }

    public static Map<String, Object> convertParams(Map<String, Object> template, Map<String, Object> refer) {
        if (CheckUtils.isEmpty(template)) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        template.forEach((k, v) -> {
            if (null == v) {
                return;
            }
            if (!(v instanceof String)) {
                result.put(k, v);
            }
            String valStr = String.valueOf(v);
            if (CheckUtils.isEmpty(valStr)) {
                return;
            }
            if (Pattern.matches("\\{\\[.*]}", valStr)) {
                if (CheckUtils.isEmpty(refer)) {
                    return;
                }
                String referKey = valStr.replaceAll("\\{\\[|]}", "");
                if (refer.containsKey(referKey)) {
                    result.put(k, refer.get(referKey));
                }
            } else {
                result.put(k, v);
            }
        });
        return result;
    }

    private static MultiValueMap<String, Object> postBody(Map<String, Object> paramsMap) {
        if (CheckUtils.isEmpty(paramsMap)) {
            return null;
        }
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
        paramsMap.forEach((k, v) -> {
            if (null == v) {
                return;
            }
            result.add(k, v);
        });
        return result;
    }
}
