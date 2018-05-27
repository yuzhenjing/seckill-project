package com.fast.pojo.seckill;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer stockcount;

    private Integer stockSale;

    private Integer stockVersion;
}
