package com.qf.shop.shop_item;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopItemApplication.class, args);
    }
    @Bean
    public Queue getQueue2(){
        return new Queue("create_html_queue");
    }
}
