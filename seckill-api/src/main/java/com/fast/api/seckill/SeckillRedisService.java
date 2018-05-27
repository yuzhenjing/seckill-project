package com.fast.api.seckill;

import com.fast.dto.seckill.StockDTO;

public interface SeckillRedisService {
    /**
     * 查询库存信息
     *
     * @param sid
     * @return
     */
    StockDTO querySeckillStock(Integer sid);

    /**
     * 更新缓存中库存信息
     *
     * @param sid
     */
    void updateSeckillStockInfo(Integer sid);

    /**
     * 设置缓存中库存信息
     *
     * @param stockDTO
     */
    void setSeckillStcok(StockDTO stockDTO);
}
