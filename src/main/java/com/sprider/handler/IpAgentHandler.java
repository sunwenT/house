package com.sprider.handler;

import com.sprider.mapping.ProxyIpMapper;
import com.sprider.pojo.ProxyIp;
import com.util.Constant;
import com.util.Common;
import com.util.WebHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by work on 2018/8/23.
 */
@Component
public class IpAgentHandler{

    @Resource
    private ProxyIpMapper proxyIpMapper;

    private String url = WebHeader.KUAI_AGENT+1;

    public IpAgentHandler(){};

    public IpAgentHandler(String url) {
        this.url = url;
    }


    /*@Override
    public void run() {
        grabHtml();

    }*/

    /**
     * 抓取页面
     */
    public String grabHtml() {
        return Common.getReqToHtml(url, WebHeader.ipAgent());
    }

    /**
     * 解析页面得到对象集合
     * 得到ip，port和验证后的延迟时间
     * @param Content
     */
    public List<ProxyIp> anaHtml(String Content) {
        String ip = "",addr = "";int port = 0,type = 0;
        List<ProxyIp> proxyIps = new ArrayList<ProxyIp>();

        /*得到ip和port*/
        Document doc = Jsoup.parse(Content);
        Element body = doc.body();
        Element tbody = body.select("tbody").first();
        Elements trs = tbody.select("tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            for (Element td : tds) {
                if (td.attr("data-title").equalsIgnoreCase("IP")) {
                    ip = td.text();
                }
                if (td.attr("data-title").equalsIgnoreCase("PORT")) {
                    port = Integer.valueOf(td.text());
                }
                if (td.attr("data-title").equalsIgnoreCase("位置")) {

                    addr = td.text();
                }
                if (td.attr("data-title").equalsIgnoreCase("类型")) {
                    type = td.text().contentEquals("高匿") ? 1 : (td.text().contentEquals("透明") ? 2 : 3);
                }
            }
                //验证ip和port
                System.out.println("正在验证IP:"+ip+" ...");
                //int delayTime = verifyIp(ip,port);
                int delayTime = 1;
                /*将验证通过的ip和port输出*/
                if (delayTime != -1) {
                    ProxyIp proxyIp = new ProxyIp();
                    proxyIp.setIp(ip);
                    proxyIp.setPort(port + "");
                    proxyIp.setAddr(addr);
                    proxyIp.setType(type);
                    proxyIp.setResTime(Float.valueOf(delayTime+"")/1000);
                    proxyIp.setLastTime(new Date());
                    proxyIps.add(proxyIp);
                }
        }
        return proxyIps;
    }

    /*验证ip的有效性,返回响应时间，-1则代表ip不可用*/
    public int verifyIp(String ip,int port){
        if (!Common.verifyIp(ip)) {
            return -1;
        }
        /*如果连接不上或者延迟时间太长则可以进行三次连接*/
        for(int i = 0; i < 3;i++) {
            int delayTime = verifyIpConn(ip, port);
            //可以连接而且延迟在30s以内
            if (delayTime != -1&&delayTime<30000) {
                return delayTime;
            }
        }
        return -1;
    }
    /*验证ip是否代理可用，并返回延时时间*/
    public int verifyIpConn(String ip,int port){
        try {
            URL url = new URL(Constant.IP_AGENT_URL);
            InetSocketAddress addr = new InetSocketAddress(ip, port);
            //创建代理服务器
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            URLConnection conn = url.openConnection(proxy);
            long stime = System.currentTimeMillis();
            conn.setConnectTimeout(20000);
            conn.connect();
            return Integer.valueOf((System.currentTimeMillis() - stime)+"");
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 持久化数据
     */
    public void persistData(List<ProxyIp> proxyIps) {
        if (proxyIps == null || proxyIps.isEmpty()) {
            return;
        }
        try {
            proxyIpMapper.insertList(proxyIps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 持久化数据
     */
    public void persistDataTest(ProxyIp proxyIp) {
        try {
            proxyIpMapper.insert(proxyIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        /*ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=1;i<=1;i++) {
            executorService.execute(new IpAgentHandler(WebHeader.KUAI_AGENT+i));
        }
        executorService.shutdown();*/










    }




}
