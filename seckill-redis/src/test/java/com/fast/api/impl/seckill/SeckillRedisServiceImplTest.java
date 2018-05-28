package com.fast.api.impl.seckill;

import com.fast.api.seckill.SeckillRedisService;
import com.fast.dto.seckill.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeckillRedisServiceImplTest {

    @Resource
    private SeckillRedisService seckillRedisService;

    @Test
    public void querySeckillStock() {
        StockDTO stockDTO = seckillRedisService.querySeckillStock(1);
        System.out.println(stockDTO);

    }

    @Test
    public void updateSeckillStockInfo() {
        seckillRedisService.updateSeckillStockInfo(1);
    }

    @Test
    public void setSeckillStcok() {
        seckillRedisService.setSeckillStcok(new StockDTO(1, 10, 0, 0));
    }
}