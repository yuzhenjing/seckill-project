package com.fast.api.impl.seckill;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fast.api.seckill.SeckillRedisService;
import com.fast.api.seckill.SeckillStockService;
import com.fast.dao.seckill.SeckillStockDAO;
import com.fast.dto.seckill.StockDTO;
import com.fast.pojo.seckill.Stock;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SeckillStockServiceImpl implements SeckillStockService {

    @Reference
    private SeckillRedisService seckillRedisService;

    @Resource
    private SeckillStockDAO seckillStockDAO;


    @Override
    public StockDTO queryStockInfo(Integer sid) {
        /**
         * 首先查询Redis中库存信息
         */
        StockDTO stockDTO = seckillRedisService.querySeckillStock(sid);
        if (stockDTO.getStockCount() == null) {
            Stock stock = seckillStockDAO.getOne(sid);
            BeanUtils.copyProperties(stock, stockDTO);
            seckillRedisService.setSeckillStcok(stockDTO);
        }
        return stockDTO;
    }

    @Override
    public void checkStockCount(Integer sid) {

        StockDTO stock = queryStockInfo(sid);
        if (stock == null || stock.getStockCount().compareTo(stock.getStockSale()) <= 0) {
            throw new RuntimeException("被抢光了 下次提前来哟！！！");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer updateStockCount(Integer sid) {

        Stock stock = seckillStockDAO.getOne(sid);
        Integer count = seckillStockDAO.updateStock(sid, stock.getStockVersion());
        if (count == 0) {
            throw new RuntimeException("更新库存失败！！！");
        }
        /**
         * 更新缓存中库存信息
         */
        seckillRedisService.updateSeckillStockInfo(sid);
        return count;
    }
}
