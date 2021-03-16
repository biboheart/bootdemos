package com.biboheart.damqp.send;

import com.biboheart.brick.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMessage {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    @Autowired
    public SendMessage(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void send(Object data) {
        // start 向MQ发送消息
        log.info("发送MQ消息: {}", data);
        rabbitTemplate.convertAndSend(topicExchange.getName(),  // 主题名称
                "test_routing", // 路由名称
                JsonUtils.obj2json(data));  // 数据
        // end 向MQ发送消息
    }
}
