package com.xiepanpan.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description:  nio实现多人聊天 服务端
 */
public class ChatServer {

    //监听通道 （老大）
    private  ServerSocketChannel serverSocketChannel;

    //选择器对象 （间谍）
    private Selector selector;

    private static final int PORT =9999;

    public ChatServer() throws IOException {
        //得到监听通道
        serverSocketChannel = ServerSocketChannel.open();

        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(PORT));

        serverSocketChannel.configureBlocking(false);

        //将选择器绑定到监听通道并监听accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        printInfo("chat server is ready...");
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }

    /**
     * 往控制台打印消息
     * @param string
     */
    private void printInfo(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("["+simpleDateFormat.format(new Date())+"]"+ string);
    }



    private void start() throws IOException {
        while (true) {
            if (selector.select(2000) == 0) {
                System.out.println("server： 没有客户端找我 我就干别的事情");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println(socketChannel.getRemoteAddress().toString().substring(1)+"上线了。。。");
                }

                //读数据
                if (selectionKey.isReadable()) {
                    readMsg(selectionKey);
                }

                //
                iterator.remove();
            }
        }
    }

    private void readMsg(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(byteBuffer);
        if (count>0) {
            String msg = new String(byteBuffer.array());
            printInfo(msg);

            //广播消息
            broadCast(socketChannel,msg);
        }
    }

    /**
     * 向所有客户端发送广播
     * @param socketChannel
     * @param msg
     */
    private void broadCast(SocketChannel socketChannel, String msg) throws IOException {
        System.out.println("服务器发送了广播");
        for (SelectionKey selectionKey:selector.keys()) {
            Channel targetChannel = selectionKey.channel();
            //是socketChannel 但不是自身  消息不广播给自己
            if (targetChannel instanceof SocketChannel && targetChannel!=socketChannel) {
                SocketChannel destChannel = (SocketChannel) targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                destChannel.write(byteBuffer);
            }
        }
    }
}
