package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品实体类
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Goods implements Serializable {

    private Integer id;
    private String title;
    private String ginfo;
    private double gcount;
    private Integer tid;
    private double allprice;
    private double privce;
    private String gimage;

}
