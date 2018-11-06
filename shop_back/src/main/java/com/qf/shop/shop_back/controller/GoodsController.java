package com.qf.shop.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @RequestMapping("/goodslist")
    @ResponseBody
    public String goodsManager(){
        System.out.println("goodslist,,,,,,,,,,");
        List<Goods> goodsList = goodsService.queryAll();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
        System.out.println("goodslist,,,,,,,,,,end");
        return "goodslist";
    }
}
