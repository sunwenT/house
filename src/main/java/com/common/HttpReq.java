package com.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by work on 2018/8/22.
 */
public class HttpReq {

    private static int suc_inx = 1;
    private static int fal_inx = 1;

    /**
     * 远程访问-get请求
     */
    public static void getReq(String url,Map header){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet method = new HttpGet(url);
        if (header != null && header.size() > 0) {
            Set<Map.Entry<String, String>> set =  header.entrySet();
            for (Map.Entry<String, String> me : set) {
                method.setHeader(me.getKey(),me.getValue());
            }
        }
        HttpResponse response = null;
        try {
            response = client.execute(method);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("========SUCCESS "+ suc_inx++ +" 次==========");
        }else{
            System.out.println("========FAIL "+ fal_inx++ +" 次==========");
        }
    }

}
