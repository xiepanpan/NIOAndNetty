package com.xiepanpan.thread.communication.num;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  测试两个线程交替执行
 */
public class TestThreadForNum {

    public static void main(String[] args) {
        new ThreadForNumOne().start();
        new ThreadForNumTwo().start();
    }
}
