package com.fast.api.impl.seckill;

import com.alibaba.dubbo.config.annotation.Service;
import com.fast.api.seckill.SeckillStockOrderService;
import com.fast.api.seckill.SeckillStockService;
import com.fast.dao.seckill.SeckillOrderDAO;
import com.fast.pojo.seckill.SeckillOrder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SeckillStockOrderServiceImpl implements SeckillStockOrderService {

    @Resource
    private SeckillOrderDAO seckillOrderDAO;

    @Resource
    private SeckillStockService seckillStockService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createSeckillOrder(Integer sid) {
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setSid(sid);
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setName("爱马仕");
        seckillOrderDAO.save(seckillOrder);
        return null;
    }
}
