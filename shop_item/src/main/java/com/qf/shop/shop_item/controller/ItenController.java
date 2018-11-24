package com.qf.shop.shop_item.controller;

import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItenController {

    @Autowired
    private Configuration configuration;

    @RequestMapping("/createHtml")
    public String createHtml(@RequestBody Goods goods, HttpServletRequest request){
        Writer out = null;
        try {
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("context",request.getContextPath());
            map.put("goods",goods);
            String path = this.getClass().getResource("/").getPath() + "static/page/" + goods.getId() + ".html";
            out = new FileWriter(path);
            template.process(map,out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
