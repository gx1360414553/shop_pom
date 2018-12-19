package com.qf.shop.shop_item.listener;

import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener( queues = "create_html_queue")
public class RabbitmqListener implements ServletContextAware {
    @Autowired
    private Configuration configuration;
    private ServletContext servletContext;
    @RabbitHandler
    public void getHandler(Goods goods){
        Writer out = null;
        try {
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("context",servletContext.getContextPath());
            map.put("goods",goods);
            System.out.println(goods);
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
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
