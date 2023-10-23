package com.example.oneapibackend.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class RocketMQProducerSimulator {
    public static void main(String[] args) throws Exception {
        // 创建一个名为 "producerGroup" 的生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup");

        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");

        // 启动生产者
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建一个消息实例，指定主题，标签和消息体
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i).getBytes());

            // 发送消息到一个Broker
            producer.send(msg);

            System.out.println("Sent message: " + msg);
        }

        // 关闭生产者实例
        producer.shutdown();
    }
}
