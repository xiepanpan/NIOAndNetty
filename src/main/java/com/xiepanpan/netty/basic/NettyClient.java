package com.xiepanpan.netty.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description:  网络客户端
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        //1.创建一个线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        //2. 创建客户端的启动助手 完成相关配置
        Bootstrap bootstrap = new Bootstrap();
        //3. 设置线程组
        bootstrap.group(eventLoopGroup)
        //4. 设置客户端通道的实现类
        .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    //5. 创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //6、往pipeline链中添加自定义的handler
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        //7、 启动客户端去连接服务器端 connect方法是异步的  syn方法是同步阻塞的
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
        //8. 关闭连接
        channelFuture.channel().closeFuture().sync();
    }
}
