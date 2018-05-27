package com.fast.dto.seckill;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class StockDTO implements Serializable {

    private static final long serialVersionUID = 8667698650837660422L;
    private Integer sid;

    private Integer stockCount;

    private Integer stockSale;

    private Integer stockVersion;

}
