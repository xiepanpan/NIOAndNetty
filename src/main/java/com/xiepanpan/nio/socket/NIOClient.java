package com.xiepanpan.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: xiepanpan
 * @Date: 2020/8/4
 * @Description:  nio实现网络通信  客户端
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //1. 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //2. 设置非阻塞方式
        socketChannel.configureBlocking(false);
        //3、 提供服务端的ip地址和端口号
        String host;
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
        //4. 连接服务器端
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("Client:连接服务器的同时 我还可以干别的事情");
            }
        }
        //5. 得到一个缓冲区并存入数据
        String msg = "hello server";
        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
        //6、发送数据
        socketChannel.write(wrap);
        System.in.read();
    }
}
