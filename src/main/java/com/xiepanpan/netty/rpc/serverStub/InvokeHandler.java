package com.xiepanpan.netty.rpc.serverStub;

import com.sun.xml.internal.bind.v2.model.core.ClassInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description:
 */
public class InvokeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo) msg;
        Class.forName()
    }
}