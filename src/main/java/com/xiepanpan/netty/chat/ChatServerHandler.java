package com.xiepanpan.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description:  自定义聊天服务端处理类
 */
public class ChatServerHandler  extends SimpleChannelInboundHandler<String> {

    public static List<Channel> channelList = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channelList.add(inChannel);
        System.out.println("[server]:"+inChannel.remoteAddress().toString().substring(1)+"上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println("[server]"+channel.remoteAddress().toString().substring(1)+"下线");
    }

    /**
     * 读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel inChannel = ctx.channel();
        for (Channel channel: channelList) {
            if (channel!=inChannel) {
                channel.writeAndFlush("["+inChannel.remoteAddress().toString().substring(1)+"]"+"说："+msg+"\n");
            }
        }
    }
}
