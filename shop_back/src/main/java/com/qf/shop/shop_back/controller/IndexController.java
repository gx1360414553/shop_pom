package com.qf.shop.shop_back.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String welcome(){
        return "index";
    }
    @RequestMapping("/topage/{pagename}")
    public String toPage(@PathVariable("pagename") String pagename){
        System.out.println(pagename + ",,,,,,,,,,,,,,");
        return pagename;
    }

}
