package com.xiepanpan.nio.chat;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author: xiepanpan
 * @Date: 2020/8/5
 * @Description:  多人聊天测试类
 */
public class TestChat {

    public static void main(String[] args) throws IOException {
        final ChatClient chatClient = new ChatClient();

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        chatClient.receiveMsg();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            chatClient.sendMsg(msg);
        }
    }


}

