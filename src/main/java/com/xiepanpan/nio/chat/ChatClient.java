package com.xiepanpan.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description:  nio实现多人聊天 客户端
 */
public class ChatClient {

    //服务器地址
    private final String HOST = "127.0.0.1";
    private int PORT =9999;
    //网络通道
    private SocketChannel socketChannel;
    //用户名
    private String username;

    public ChatClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(HOST, PORT);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("Client：连接服务器端的同时 我还可以干别的事情");
            }
        }

        username = socketChannel.getLocalAddress().toString().substring(1);

        System.out.println("=======client"+username+" is ready========");
    }

    /**
     * 向服务端发送数据
     * @param msg
     */
    public void sendMsg(String msg) throws IOException {

        //发送 bye表示聊天结束
        if (msg.equalsIgnoreCase("bye")) {
            socketChannel.close();
            return;
        }

        msg = username+"说："+msg;
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(byteBuffer);

    }

    /**
     * 从服务器端接收数据
     */
    public void receiveMsg() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int size = socketChannel.read(byteBuffer);
        if (size>0) {
            String msg = new String(byteBuffer.array());
            System.out.println(msg.trim());
        }
    }
}

