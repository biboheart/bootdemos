package com.biboheart.damqp.send;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {
    private final static String TopicExchange = "topic_exchange"; // 通道名称

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TopicExchange);
    }
}
