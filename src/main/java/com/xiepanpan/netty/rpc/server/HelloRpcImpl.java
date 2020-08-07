package com.xiepanpan.netty.rpc.server;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description:
 */
public class HelloRpcImpl implements HelloRpc {
    @Override
    public String hello(String name) {
        return "hello,"+name;
    }
}