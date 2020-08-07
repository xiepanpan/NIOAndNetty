package com.xiepanpan.netty.rpc.clientStub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description: 客户端业务处理类
 */
public class ResultHandler extends ChannelInboundHandlerAdapter {

    private Object response;
    public Object getResponse(){
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
        ctx.close();
    }
}