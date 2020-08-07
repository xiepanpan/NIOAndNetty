package com.xiepanpan.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description: bio 客户端程序
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        while(true) {

            //创建socket对象
            Socket socket = new Socket("127.0.0.1",9999);

            //从连接中取出输出流并发送消息
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            outputStream.write(msg.getBytes());

            //从连接中取出输入流并接收回话
            //阻塞
            InputStream inputStream = socket.getInputStream();
            byte[] bytes= new byte[20];
            inputStream.read(bytes);
            System.out.println("长安说："+new String(bytes).trim());
        }
    }
}
