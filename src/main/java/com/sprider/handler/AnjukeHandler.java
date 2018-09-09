package com.sprider.handler;

/**
 * 1、定时并发执行
 * 2、先使用自己的ip爬取，如果会被封使用代理ip
 * 3、四大直辖市和临沂市
 * 4、多网站
 * 5、对接小程序和朋友圈
 * 6、所有县区
 */
public class AnjukeHandler{

    public void grabHtml(){
        /*try{
            Http.getReq(WebHeader.linyi_lanshan_2,WebHeader.anjuke());
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

    public void anaHtml(){

    }

    public void dealData(){

    }

    public static void main(String[] args) {
        AnjukeHandler anjuke = new AnjukeHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int inx = 1;
                for(int i=0;i<100;i++) {
                    System.out.println("=======第"+inx+++"次爬虫开始=========");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    anjuke.grabHtml();
                }
            }
        }).start();

    }

}
