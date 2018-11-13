package com.qf.shop.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Reference
    private IGoodsService goodsService;

    @Value("${image.path}")
    private String path;

    @RequestMapping("/goodslist")
    public String goodsManager(Model model){
        List<Goods> goodsList = goodsService.queryAll();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
        model.addAttribute("goods",goodsList);
        model.addAttribute("path",path);
        return "goodslist";
    }
    @RequestMapping("/goodsadd")
    public String goodsAdd(@RequestParam("file") MultipartFile file,Goods goods) throws IOException {
        System.out.println(goods);
        StorePath path = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), "JPG", null);
        System.out.println(path.getFullPath());
        goods.setGimage(path.getFullPath());
        goods = goodsService.addGoods(goods);

        HttpClientUtil.sendJsonPost("http://localhost:8082/solr/add",new Gson().toJson(goods));


        return "redirect:/goods/goodslist";
    }

    @RequestMapping("/queryNew")
    @ResponseBody
    @CrossOrigin
    public List<Goods> queryNew(){
        List<Goods> goods = goodsService.queryNew();
        return goods;
    }
}
