package com.fast.api.impl.seckill;

import com.alibaba.dubbo.config.annotation.Service;
import com.fast.api.seckill.SeckillRedisService;
import com.fast.constant.SeckillStockConstant;
import com.fast.dto.seckill.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Service
public class SeckillRedisServiceImpl implements SeckillRedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public StockDTO querySeckillStock(Integer sid) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        Integer stockCount = Integer.parseInt(operations.get(SeckillStockConstant.STOCK_COUNT + sid));
        Integer stockSale = Integer.parseInt(operations.get(SeckillStockConstant.STOCK_SALE + sid));
        Integer stockVersion = Integer.parseInt(operations.get(SeckillStockConstant.STOCK_VERSION + sid));
        return new StockDTO(sid, stockCount, stockSale, stockVersion);
    }

    @Override
    public void updateSeckillStockInfo(Integer sid) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.increment(SeckillStockConstant.STOCK_SALE + sid, 1);
        operations.increment(SeckillStockConstant.STOCK_VERSION + sid, 1);

    }

    @Override
    public void setSeckillStcok(StockDTO stockDTO) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        Integer sid = stockDTO.getSid();
        operations.set(SeckillStockConstant.STOCK_COUNT + sid, stockDTO.getStockCount().toString());
        operations.set(SeckillStockConstant.STOCK_SALE + sid, stockDTO.getStockSale().toString());
        operations.set(SeckillStockConstant.STOCK_VERSION + sid, stockDTO.getStockVersion().toString());
    }
}
