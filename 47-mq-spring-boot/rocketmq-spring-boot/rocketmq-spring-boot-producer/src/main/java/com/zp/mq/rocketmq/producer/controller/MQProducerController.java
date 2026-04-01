package com.zp.mq.rocketmq.producer.controller;

import com.zp.mq.rocketmq.producer.service.MQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mq")
public class MQProducerController {

    private final MQProducer producer;

    @GetMapping("/send")
    public String send(@RequestParam("msg") String msg) {
        producer.sendSync("TestTopic", msg);
        return "OK";
    }

}
