package com.xiepanpan.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author: xiepanpan
 * @Date: 2020/8/4
 * @Description: nio实现网络通信 服务端
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        //1、 得到一个ServerSocketChannel  老大
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2、 得到一个Selector对象 间谍
        Selector selector = Selector.open();
        //3. 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //4、设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5. 把ServerSocketChannel对象注册给Selector对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6. 干活
        while (true) {
            //监控客户端
            if (selector.select(2000)==0) {
                System.out.println("server: 没有客户端搭理我 我干别的事");
                continue;
            }
            //得到SelectionKey 判断通道里的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    //客户端连接请求事件
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }

                //读取客户端数据事件
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("客户端发来数据："+new String(byteBuffer.array()));
                }

                //手动从集合中移除当前key 防止重复处理
                iterator.remove();
            }
        }
    }
}
