package com.sprider.handler;

/**
 * 1、http请求得到html文件
 * 2、正则得到需要的数据
 * 3、将数据存入数据库
 */
public class AnjukeHandler implements SpriderInter{

    @Override
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
