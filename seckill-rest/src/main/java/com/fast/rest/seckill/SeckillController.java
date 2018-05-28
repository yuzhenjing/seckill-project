package com.fast.rest.seckill;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fast.api.seckill.SeckillService;
import com.fast.api.seckill.SeckillStockService;
import com.fast.dto.seckill.StockDTO;
import com.fast.vo.SeckillResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Reference
    private SeckillStockService seckillStockService;
    @Reference
    private SeckillService seckillService;

    /**
     * 查询库存信息
     *
     * @param sid
     * @return
     */
    @GetMapping("/getStockInfo/{sid}")
    public Map<String, Object> getStockInfo(@PathVariable Integer sid) {
        StockDTO stockDTO = seckillStockService.queryStockInfo(sid);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("总库存", stockDTO.getStockCount());
        resultMap.put("销量", stockDTO.getStockSale());
        return resultMap;
    }

    @GetMapping("/executeSeckill/{sid}")
    public SeckillResultVO executeSeckill(@PathVariable Integer sid) throws Exception {
        seckillService.executeSeckill(sid);
        return null;
    }

}
