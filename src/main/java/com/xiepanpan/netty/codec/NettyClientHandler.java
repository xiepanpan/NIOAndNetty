package com.xiepanpan.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BookMessage.Book book = BookMessage.Book.newBuilder().setId(1).setName("恋爱必修课").build();
        ctx.writeAndFlush(book);
    }
}