package com.qf.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 商品实体类
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private Integer id;
    private String title;
    private String ginfo;
    private double gcount;
    private Integer tid = 1;
    private double allprice;
    private double price;
    private String gimage;


}
