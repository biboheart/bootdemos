package com.biboheart.demos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class WebfluxController {

    @GetMapping("/hello")
    public Mono<?> hello() {
        return Mono.just("demo");
    }

    @GetMapping("/list")
    public Mono<?> list() {
        // String url = "http://192.168.2.127/platformdocking/platformapi/docking/inspection/list";
        WebClient client = WebClient.create("http://192.168.2.127/platformdocking/platformapi/docking/inspection/list?number=2020045180&access_token=12045320-93c7-4083-8d6a-6214a9580c98");
        return client.get().exchange();
    }
}
