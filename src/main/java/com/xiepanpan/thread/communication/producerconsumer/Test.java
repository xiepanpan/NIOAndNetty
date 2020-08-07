package com.xiepanpan.thread.communication.producerconsumer;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  测试模拟生产者消费者模式 生产者消费者一对一
 */
public class Test {

    public static void main(String[] args) {
        new Farmer().start();
        new Child().start();
    }

}
