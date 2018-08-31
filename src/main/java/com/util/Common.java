package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by work on 2018/8/24.
 */
public class Common {

    /**
     * 正则验证ip
     */
    public static boolean verifyIp(String ip) {
        if (ip == null || ip.length()<7 || ip.length() > 15) {
            return false;
        }
        String regex = "((25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(ip).matches();
    }

    /**
     * 远程访问-get请求返回页面
     */
    public static String getReqToHtml(String url,Map header){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet method = new HttpGet(url);
        if (header != null && header.size() > 0) {
            Set<Map.Entry<String, String>> set =  header.entrySet();
            for (Map.Entry<String, String> me : set) {
                method.setHeader(me.getKey(),me.getValue());
            }
        }
        HttpResponse response = null; HttpEntity entity = null;String content = "";
        try {
            response = client.execute(method);
            if (response == null || response.getStatusLine().getStatusCode() != 200) {
                return "";
            }
            entity = response.getEntity();
            if (entity == null) {
                return "";
            }
            content = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
