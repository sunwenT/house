package com.sprider.pojo;

import java.util.Date;

/**
 * Created by work on 2018/8/24.
 */
public class ProxyIp {
    private Integer id;
    private String ip;
    private String port;
    private Integer type;
    private String addr;
    private Float resTime;
    private Date lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Float getResTime() {
        return resTime;
    }

    public void setResTime(Float resTime) {
        this.resTime = resTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
