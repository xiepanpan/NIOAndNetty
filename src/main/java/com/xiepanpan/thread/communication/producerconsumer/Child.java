package com.xiepanpan.thread.communication.producerconsumer;

import java.util.Random;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description: 孩子类 相当于消费者
 */
public class Child extends Thread{

    @Override
    public void run() {
        while (true) {
            synchronized (Basket.basket) {

                //篮子里没有水果 让小孩休息一哈
                if (Basket.basket.size()==0 ) {
                    try {
                        Basket.basket.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //小孩吃水果
                Basket.basket.remove("apple");
                System.out.println("小孩吃了一个水果 目前篮子里有"+Basket.basket.size()+"个水果");

                //唤醒农夫继续放水果
                Basket.basket.notify();
            }

            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
