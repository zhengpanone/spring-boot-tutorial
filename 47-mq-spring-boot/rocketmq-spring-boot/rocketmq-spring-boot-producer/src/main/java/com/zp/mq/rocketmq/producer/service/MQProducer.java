package com.zp.mq.rocketmq.producer.service;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MQProducer {

    private final RocketMQTemplate rocketMQTemplate;

    // 同步发送
    public SendResult sendSync(String topic, String msg) {
        return rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msg).build());
    }

    // 异步发送
    public void sendAsync(String topic, String msg) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(msg).build(), new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功: " + sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.err.println("发送失败: " + e.getMessage());
            }
        });
    }

    // 单向发送（不关心结果）
    public void sendOneway(String msg) {
        rocketMQTemplate.sendOneWay("TestTopic", MessageBuilder.withPayload(msg).build());
    }

    // 延迟消息（level 1~18）
    public void sendDelay(String msg) {
        rocketMQTemplate.syncSend("TestTopic", MessageBuilder.withPayload(msg).build(), 3000, 3);
    }
}
