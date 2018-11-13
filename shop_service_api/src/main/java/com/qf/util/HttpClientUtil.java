package com.qf.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpClientUtil {


    public static String sendJsonPost(String url, String json){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
        httpPost.setEntity(new StringEntity(json,"utf-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseStr = EntityUtils.toString(entity);
        System.out.println(responseStr);
        return responseStr;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(get);

        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 10];
        int len;
        while ((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        byte[] bytes = out.toByteArray();
        System.out.println("获得的响应内容为:" + new String(bytes,"utf-8"));
    }
}
