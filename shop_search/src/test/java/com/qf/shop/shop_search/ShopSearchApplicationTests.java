package com.qf.shop.shop_search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSearchApplicationTests {
    @Autowired
    private SolrClient solrClient;
    @Test
   public void solrAdd(){

        try {
        for (int i = 2; i <100; i++) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id",i);
            solrInputDocument.addField("gtitle","华为手机"+i);
            solrInputDocument.addField("ginfo","华为手机，手机中的战斗机"+i);
            solrInputDocument.addField("gprice",800+i);
            solrInputDocument.addField("gimage","http://www.baidu.com"+i);
            solrClient.add(solrInputDocument);
        }

            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("ginfo:战斗5 AND gtitle:5");

        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList results = response.getResults();

        for (SolrDocument solrDocument : results) {
            System.out.println("---------------------------------------------");
            String id = (String) solrDocument.getFieldValue("id");
            String gtitle = (String) solrDocument.getFieldValue("gtitle");
            String ginfo = (String) solrDocument.getFieldValue("ginfo");
            float gprice = (float) solrDocument.getFieldValue("gprice");
            String gimage = (String) solrDocument.getFieldValue("gimage");
            System.out.println(id);
            System.out.println(gtitle);
            System.out.println(ginfo);
            System.out.println(gprice);
            System.out.println(gimage);
        }
    }
    @Test
    public void delete() throws IOException, SolrServerException {
        solrClient.deleteByQuery("gtitle:华为");
        solrClient.commit();
    }
}
