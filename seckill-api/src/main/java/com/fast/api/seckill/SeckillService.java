package com.fast.api.seckill;

public interface SeckillService {
    /**
     * 执行秒杀
     *
     * @param sid
     * @throws Exception
     */
    void executeSeckill(Integer sid) throws Exception;
}
