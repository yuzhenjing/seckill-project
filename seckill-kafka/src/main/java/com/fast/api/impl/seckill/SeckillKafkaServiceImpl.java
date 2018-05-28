package com.fast.api.impl.seckill;

import com.alibaba.dubbo.config.annotation.Service;
import com.fast.api.seckill.SeckillKafkaService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

@Service
public class SeckillKafkaServiceImpl implements SeckillKafkaService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendSeckillTask(Integer sid) {
        kafkaTemplate.send(new ProducerRecord("SECKILL", sid + ""));

    }
}
