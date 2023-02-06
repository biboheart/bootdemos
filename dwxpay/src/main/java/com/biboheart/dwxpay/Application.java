package com.biboheart.dwxpay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestControllerAdvice
@Slf4j
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
