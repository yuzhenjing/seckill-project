package com.fast.dao.seckill;

import com.fast.pojo.seckill.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillStockDAO extends JpaRepository<Stock, Integer> {
}
