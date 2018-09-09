package com.sprider.mapping;

import com.sprider.pojo.ProxyIp;

import java.util.List;

/**
 * Created by work on 2018/8/31.
 */
public interface ProxyIpMapper {
    void insertList(List<ProxyIp> proxyIps);
    void insert(ProxyIp proxyIp);
}
