package com.fast.dao.seckill;

import com.fast.pojo.seckill.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeckillStockDAO extends JpaRepository<Stock, Integer> {

    /**
     * @param sid
     * @param stockVersion
     * @return
     */
    @Query("UPDATE Stock  s set s.stockVersion = stock_version+1,s.stockSale = stock_sale+1 where id= :sid  and stock_version = :stockVersion ")
    @Modifying
    Integer updateStock(@Param("sid") Integer sid, @Param("stockVersion") Integer stockVersion);
}
