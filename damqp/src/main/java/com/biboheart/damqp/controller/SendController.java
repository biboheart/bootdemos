package com.biboheart.damqp.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.damqp.send.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SendController {
    private final SendMessage sendMessage;

    @Autowired
    public SendController(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    public BhResponseResult<?> test() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("name", "bibo");
        data.put("age", 36);
        sendMessage.send(data);
        return new BhResponseResult<>(0, "success", "发送消息");
    }
}
