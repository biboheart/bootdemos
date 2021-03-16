package com.biboheart.damqp.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ReceiverConfiguration.ReceiverQueue)
@Slf4j
public class TopicReceiver {
    @RabbitHandler
    public void onMessage(String message) {
        log.info("接收到topic消息:{}", message);
    }
}
