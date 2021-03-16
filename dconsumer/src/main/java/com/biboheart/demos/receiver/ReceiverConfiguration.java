package com.biboheart.demos.receiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfiguration {
    private final static String TopicExchange = "topic_exchange"; // 通道名称
    public final static String ReceiverQueue = "consumer_queue";    // 取消息队列

    @Bean
    public TopicExchange receiverTopicExchange() {
        return new TopicExchange(TopicExchange);
    }

    @Bean
    public Queue receiverQueue() {
        return new Queue(ReceiverQueue, false);
    }

    @Bean
    @Autowired
    public Binding receiverQueueBindingTopicExchange(Queue receiverQueue, TopicExchange receiverTopicExchange) {
        return BindingBuilder.bind(receiverQueue)
                .to(receiverTopicExchange)
                .with("#");
    }
}
