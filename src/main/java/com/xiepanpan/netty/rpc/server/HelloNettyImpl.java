package com.xiepanpan.netty.rpc.server;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description:
 */
public class HelloNettyImpl implements HelloNetty {
    @Override
    public String hello() {
        return "hello netty";
    }
}