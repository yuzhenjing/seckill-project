package com.fast.consume.seckill;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fast.api.seckill.SeckillStockOrderService;
import com.fast.api.seckill.SeckillStockService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Slf4j
@Component
public class SeckillKafkaConsumer {

    @Reference
    private SeckillStockService seckillStockService;
    @Reference
    private SeckillStockOrderService seckillStockOrderService;
    /**
     * 线程池
     */
    private ExecutorService threadPool;

    {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("consumer-pool-%d").build();
        threadPool = new ThreadPoolExecutor(4, 100, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    @KafkaListener(topics = "SECKILL")
    public void consumerSeckill(ConsumerRecord<String, String> record) {
        final int sid = Integer.parseInt(record.value());
        threadPool.submit(() -> executeSeckill(sid));
    }
    private void executeSeckill(Integer sid) {
        /**
         * 校验库存
         */
        seckillStockService.checkStockCount(sid);

        /**
         * 更新库存
         */
        seckillStockService.updateStockCount(sid);

        /**
         * 创建订单
         */
        seckillStockOrderService.createSeckillOrder(sid);
    }

}
