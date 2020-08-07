package com.xiepanpan.thread.num;

import static javafx.scene.input.KeyCode.M;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  输出数字1的线程
 */
public class ThreadForNumOne extends Thread{

    public void run() {
        for (int i = 0; i < 10; i++) {

            synchronized (MyLock.object) {
                System.out.println(1);
                MyLock.object.notify();
                try {
                    MyLock.object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
