package com.fast.dao.seckill;

import com.fast.pojo.seckill.SeckillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillOrderDAO extends JpaRepository<SeckillOrder, Integer> {
}
