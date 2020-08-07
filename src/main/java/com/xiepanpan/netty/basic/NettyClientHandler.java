package com.xiepanpan.netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description: 自定义客户单业务处理类
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     *  通道就绪事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client: "+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好 熟人介绍的", CharsetUtil.UTF_8));
    }

    /**
     * 读取数据事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器端发来的消息："+byteBuf.toString(CharsetUtil.UTF_8));
    }
}
