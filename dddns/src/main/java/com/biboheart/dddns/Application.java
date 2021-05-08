package com.biboheart.dddns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class Application implements ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(">>>>>>>>>>>>>>ddns 启动完成<<<<<<<<<<<<<<");
    }
}