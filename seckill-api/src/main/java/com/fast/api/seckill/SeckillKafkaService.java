package com.fast.api.seckill;

public interface SeckillKafkaService {
    /**
     * 创建秒杀任务
     *
     * @param sid
     */
    void sendSeckillTask(Integer sid);
}
