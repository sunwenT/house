package com.sprider.handler;

import com.common.HttpReq;
import com.util.HeaderUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by work on 2018/8/23.
 */
public class IpAgentHandler implements Runnable{
    private volatile int page = 1;
    public String a = "";
    @Override
    public void run() {
        while(page<=15){
            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            grabHtml(page);
                System.out.println(Thread.currentThread().getName()+":"+page);
                page++;
            }

        }
    }

    public void grabHtml(int page) {
            HttpReq.getReq(HeaderUtil.KUAI_AGENT+"/"+page,HeaderUtil.ipAgent());

    }

    public void anaHtml() {

    }

    public void dealData() {

    }

    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();
        IpAgentHandler ipAgentHandler = new IpAgentHandler();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++) {
            executorService.execute(ipAgentHandler);
        }
//        new IpAgentHandler().page = 1;
        executorService.shutdown();
        System.out.println(System.currentTimeMillis()-starttime);
    }




}
