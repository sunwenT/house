package com.sprider.handler;

import com.sprider.pojo.ProxyIp;
import com.util.WebHeader;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * sprider起始类
 */
@Component
public class StartHandler implements ApplicationListener<ContextRefreshedEvent>{
    @Resource
    private IpAgentHandler ipAgentHandler;

    /**
     * 下一步
     * 1、加入定时线程池，并行定时爬取数据
     * 2、爬取多个网站的代理ip
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //除了root容器外，其子容器project-servlet.xml也会调用此监听，所以做一个判断，只允许父容器执行后续代码
        if (event.getApplicationContext().getParent() != null) {
            return;
        }
        try {
            String cont = ipAgentHandler.grabHtml();
            if (cont==null||cont.isEmpty()) {
                System.out.println("html爬取失败！");
            }
            List<ProxyIp> proxyIps = ipAgentHandler.anaHtml(cont);
            if (proxyIps == null||proxyIps.size()<1) {
                System.out.println("ip均无效！");
            }
            System.out.println(proxyIps.size()+"个有效的ip正在存入数据库！");
            ipAgentHandler.persistData(proxyIps);
            System.out.println("存储完成！");
        } catch (Exception e) {
            System.out.println("存储失败！");
            e.printStackTrace();
        }
    }
}
