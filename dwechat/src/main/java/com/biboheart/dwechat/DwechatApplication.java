package com.biboheart.dwechat;

import com.biboheart.brick.exception.BhException;
import com.biboheart.brick.model.BhResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RestControllerAdvice
@Slf4j
public class DwechatApplication implements ApplicationListener<ContextRefreshedEvent> {
    private final RestTemplateBuilder builder;

    @Autowired
    public DwechatApplication(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    public static void main(String[] args) {
        SpringApplication.run(DwechatApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(">>>>>>>>>>>>>>启动完成<<<<<<<<<<<<<<");
    }

    @Bean
    public RestTemplate restTemplate() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return builder.additionalMessageConverters(stringHttpMessageConverter).build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BhResponseResult<?> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        if (e instanceof BhException) {
            return new BhResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        } else {
            log.error("Request path : [url:{}, method:{}] 发生异常 => [异常类：{}, 异常信息:{}]", req.getRequestURI(),
                    req.getMethod(), e.getClass(), e.getMessage());
            return new BhResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作异常", e.getMessage());
        }
    }
}
