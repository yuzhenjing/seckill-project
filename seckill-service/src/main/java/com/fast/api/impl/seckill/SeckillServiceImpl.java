package com.fast.api.impl.seckill;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fast.api.seckill.SeckillKafkaService;
import com.fast.api.seckill.SeckillService;
import com.fast.api.seckill.SeckillStockOrderService;
import com.fast.api.seckill.SeckillStockService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private SeckillStockService seckillStockService;

    @Resource
    private SeckillStockOrderService seckillStockOrderService;

    @Reference
    private SeckillKafkaService seckillKafkaService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeSeckill(Integer sid) throws Exception {


        /**
         * kafka 异步处理
         */
        seckillKafkaService.sendSeckillTask(sid);

//
//        /**
//         * 校验库存
//         */
//        seckillStockService.checkStockCount(sid);
//
//        /**
//         * 更新库存
//         */
//        seckillStockService.updateStockCount(sid);
//
//        /**
//         * 创建订单
//         */
//        seckillStockOrderService.createSeckillOrder(sid);

    }
}
