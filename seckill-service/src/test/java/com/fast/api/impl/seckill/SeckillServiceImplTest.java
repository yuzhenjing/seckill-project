package com.fast.api.impl.seckill;

import com.fast.api.seckill.SeckillService;
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
 * @Date: 2018/5/28 9:50
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {

    @Resource
    private SeckillService seckillService;

    @Test
    public void executeSeckill() throws Exception {
        seckillService.executeSeckill(1);
    }
}