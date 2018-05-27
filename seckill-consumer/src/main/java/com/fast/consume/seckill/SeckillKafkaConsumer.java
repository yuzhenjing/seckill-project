package com.fast.consume.seckill;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SeckillKafkaConsumer {

    @KafkaListener(topics = "SECKILL")
    public void consumerSeckill(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
    }
}
