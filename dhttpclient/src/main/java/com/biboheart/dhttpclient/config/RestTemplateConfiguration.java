package com.biboheart.dhttpclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfiguration {
    private final RestTemplateBuilder builder;

    @Autowired
    public RestTemplateConfiguration(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @Bean
    public RestTemplate restTemplate() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return builder.additionalMessageConverters(stringHttpMessageConverter).build();
    }
}
