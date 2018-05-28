package com.fast.api.impl.seckill;

import com.fast.api.seckill.SeckillStockService;
import com.fast.dto.seckill.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Copyright (C),2018-永达金融
 *
 * @author: yuzhenjing@ydfinance.com.cn
 * @Date: 2018/5/28 9:07
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillStockServiceImplTest {


    @Resource
    private SeckillStockService seckillStockService;


    @Test
    public void queryStockInfo() {
        final StockDTO stockDTO = seckillStockService.queryStockInfo(1);
        System.out.println(stockDTO.toString());

    }

    @Test
    public void checkStockCount() {
        seckillStockService.checkStockCount(1);
    }

    @Test
    public void updateStockCount() {
        seckillStockService.updateStockCount(1);
    }
}