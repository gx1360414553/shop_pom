package com.qf.shop.shop_item;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopItemApplicationTests {

    @Autowired
    private Configuration configuration;
    @Test
    public void contextLoads() throws Exception {
        Template template = configuration.getTemplate("hello.ftl");
        Map<String, String> map = new HashMap<>();
        map.put("key","hello");
        Writer writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\hello.html");
        template.process(map,writer);
        writer.close();
    }

}
