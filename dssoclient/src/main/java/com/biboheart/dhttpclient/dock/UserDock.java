package com.biboheart.dhttpclient.dock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class UserDock {
    private final RestTemplate restTemplate;

    @Autowired
    public UserDock(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*private final OAuth2RestTemplate oauth2RestTemplate;

    @Autowired
    public UserDock(OAuth2RestTemplate oauth2RestTemplate) {
        this.oauth2RestTemplate = oauth2RestTemplate;
    }*/

    public String request() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(null, headers);;
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange("http://192.168.2.105:8081/api/demo", HttpMethod.GET, request, String.class);
            System.out.println(response.getStatusCode());
            return response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
