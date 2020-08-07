package com.xiepanpan.thread.communication.producerconsumer;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  农民类 相当于生产者
 */
public class Farmer extends Thread{

    public void run() {
        while (true) {
            synchronized (Basket.basket) {
                //篮子满了就不要放了 让农夫休息一哈
                if(Basket.basket.size()==10) {
                    try {
                        Basket.basket.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //往篮子里放水果
                Basket.basket.add("apple");
                System.out.println("农夫放了一个水果，目前篮子里有"+Basket.basket.size()+"个水果");

                //唤醒小孩继续吃
                Basket.basket.notify();

            }

            //模拟控制速度
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
