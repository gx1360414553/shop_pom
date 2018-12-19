package com.qf.shop.shop_back;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@DubboComponentScan("com.qf.shop.shop_back.controller")
@Import(FdfsClientConfig.class)
public class ShopBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBackApplication.class, args);
    }
    @Bean
    public Queue getQueue1(){
        return new Queue("solr_index_queue");
    }
    @Bean
    public Queue getQueue2(){
        return new Queue("create_html_queue");
    }
    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("goods_exchange");
    }
    @Bean
    public Binding getBinding1(Queue getQueue1, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getQueue1).to(getFanoutExchange);
    }
    @Bean
    public Binding getBinding2(Queue getQueue2, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getQueue2).to(getFanoutExchange);
    }
}
