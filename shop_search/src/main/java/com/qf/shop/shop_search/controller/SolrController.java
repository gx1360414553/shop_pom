package com.qf.shop.shop_search.controller;

import com.qf.entity.Goods;
import com.qf.entity.SolrPage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/add")
    @ResponseBody
    public boolean solrAdd(@RequestBody Goods goods){
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
            return true;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/query")
    public String solrQuery(String keyword, SolrPage<Goods> solrPage, Model model){
        System.out.println("keyword:" + keyword);
        SolrQuery query = new SolrQuery();
        if (keyword == null || keyword.trim().equals("")){
            query.setQuery("*:*");
        }else {
            query.setQuery("goods_info:" + keyword);
        }

        query.setHighlight(true);

        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        query.addHighlightField("gtitle");

//        query.setHighlightSnippets(3);
//        query.setHighlightFragsize(4);

        query.setStart((solrPage.getPage() - 1) * solrPage.getPageSize());
        query.setRows(solrPage.getPageSize());

        QueryResponse query1= null;
        List<Goods> list = new ArrayList<>();
        try {
            query1 = solrClient.query(query);

            Map<String, Map<String, List<String>>> highlighting = query1.getHighlighting();
            Set<Map.Entry<String, Map<String, List<String>>>> entries = highlighting.entrySet();
            for (Map.Entry<String, Map<String, List<String>>> entry : entries) {
                System.out.println("key:" + entry.getKey());
                System.out.println("value:" + entry.getValue());
                System.out.println("-------------------------");
            }


            SolrDocumentList results = query1.getResults();

            long pageNum = results.getNumFound();
            solrPage.setPageSum((int)pageNum);
            solrPage.setPageCount(solrPage.getPageSum() % solrPage.getPageSize()  == 0 ?
                    solrPage.getPageSum() / solrPage.getPageSize():solrPage.getPageSum() / solrPage.getPageSize() + 1);

            for (SolrDocument result : results) {
                Goods goods = new Goods();
                goods.setId(Integer.parseInt(result.getFieldValue("id") + ""));
                goods.setTitle(result.getFieldValue("gtitle") + "");
                goods.setPrice(Float.parseFloat(result.getFieldValue("gprice") + ""));
                goods.setGimage(result.getFieldValue("gimage") + "");

                if (highlighting.containsKey(goods.getId() + "")){
                    String s = "";
                    List<String> gtitle = highlighting.get(goods.getId() + "").get("gtitle");
                    for (String s1 : gtitle) {
                        s += s1 + "....";
                    }
                    goods.setTitle(s);
                }

                list.add(goods);
            }
            solrPage.setDatas(list);
            model.addAttribute("page",solrPage);
            model.addAttribute("keyword",keyword);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "searchlist";
    }
}
