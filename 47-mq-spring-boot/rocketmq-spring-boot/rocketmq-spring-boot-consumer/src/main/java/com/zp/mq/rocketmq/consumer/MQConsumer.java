package com.zp.mq.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = "TestTopic",
        consumerGroup = "TestGroup"
)
public class MQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("收到消息: " + message);
    }
}