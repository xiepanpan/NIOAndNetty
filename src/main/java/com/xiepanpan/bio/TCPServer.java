package com.xiepanpan.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description: bio 服务端程序  先启动
 */
public class TCPServer {

    public static void main(String[] args) throws IOException {

        // 创建ServerSocket 对象
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            //监听客户端
            System.out.println("启动服务端。。");
            //这里阻塞
            Socket socket = serverSocket.accept();
            System.out.println("已连接客户端");
            //从连接中取出输入流的接收消息
            //阻塞
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[10];
            inputStream.read(bytes);
            String clientIp = serverSocket.getInetAddress().getHostAddress();

            System.out.println(clientIp+"说："+new String(bytes).trim());

            //从连接中取出输出流并回复
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("收到".getBytes());
            //关闭
            socket.close();

        }
    }



}
