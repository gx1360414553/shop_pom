package com.qf.shop.shop_search;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSearchApplication.class, args);
    }
    @Bean
    public Queue getQueue1(){
        return new Queue("solr_index_queue");
    }
}
