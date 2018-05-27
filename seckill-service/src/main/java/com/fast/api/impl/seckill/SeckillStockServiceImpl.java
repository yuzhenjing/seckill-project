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
            throw new RuntimeException("库存不足。。。。");
        }
    }

    @Override
    public Integer updateStockCount(Integer sid) {

        /**
         * 更新缓存中库存信息
         */
        seckillRedisService.updateSeckillStockInfo(sid);
        Stock stock = seckillStockDAO.getOne(sid);
        stock.setStockSale(stock.getStockSale() + 1);
        stock.setStockVersion(stock.getStockVersion() + 1);
        seckillStockDAO.saveAndFlush(stock);
        return null;
    }
}
