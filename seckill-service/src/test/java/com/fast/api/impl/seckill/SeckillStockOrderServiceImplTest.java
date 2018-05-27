package com.fast.api.impl.seckill;

import com.fast.api.seckill.SeckillStockOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillStockOrderServiceImplTest {

    @Resource
    private SeckillStockOrderService seckillStockOrderService;

    @Test
    public void createSeckillOrder() {
        seckillStockOrderService.createSeckillOrder(1);
    }
}