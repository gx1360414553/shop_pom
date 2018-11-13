package com.qf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class SolrPage<T> {

    private Integer page = 1;//当前页
    private Integer pageSize = 10;//每页显示多少条
    private Integer pageCount;//总页码
    private Integer pageSum;//总条数
    private List<T> datas;//当前页的数据
}
