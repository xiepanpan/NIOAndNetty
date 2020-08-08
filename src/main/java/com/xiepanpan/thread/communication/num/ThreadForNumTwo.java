package com.xiepanpan.thread.communication.num;


/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  输出数字2的线程
 */
public class ThreadForNumTwo extends Thread{

    public void run() {
        for (int i = 0; i < 10; i++) {

            synchronized (MyLock.object) {
                System.out.println(2);
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
