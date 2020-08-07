package com.xiepanpan.netty.rpc.client;

import com.xiepanpan.netty.rpc.clientStub.NettyRpcProxy;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description: 服务调用方
 */
public class TestNettyRpc {

    public static void main(String[] args) {
        HelloNetty helloNetty = (HelloNetty) NettyRpcProxy.create(HelloNetty.class);
        System.out.println(helloNetty.hello());

        HelloRpc helloRpc = (HelloRpc) NettyRpcProxy.create(HelloRpc.class);
        System.out.println(helloRpc.hello("RPC"));
    }
}