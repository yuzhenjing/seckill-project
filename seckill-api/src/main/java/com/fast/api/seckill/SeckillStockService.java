package com.fast.api.seckill;

import com.fast.dto.seckill.StockDTO;

public interface SeckillStockService {
    /**
     * 查询库存存息
     *
     * @param sid
     * @return
     */
    StockDTO queryStockInfo(Integer sid);

    /**
     * 校验库存
     *
     * @param sid
     */
    void checkStockCount(Integer sid);

    /**
     * 更新库存
     *
     * @param sid
     */
    Integer updateStockCount(Integer sid);
}
