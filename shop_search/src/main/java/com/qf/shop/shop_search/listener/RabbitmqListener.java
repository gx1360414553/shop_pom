package com.qf.shop.shop_search.listener;

import com.qf.entity.Goods;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener( queues = "solr_index_queue")
public class RabbitmqListener {
    @Autowired
    private SolrClient solrClient;
    @RabbitHandler
    public void getHandler(Goods goods){
        System.out.println(goods);
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id",goods.getId());
        solrInputDocument.addField("gtitle",goods.getTitle());
        solrInputDocument.addField("gimage",goods.getGimage());
        solrInputDocument.addField("ginfo",goods.getGinfo());
        solrInputDocument.addField("gprice",goods.getPrice());

        try {
            solrClient.add(solrInputDocument);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
