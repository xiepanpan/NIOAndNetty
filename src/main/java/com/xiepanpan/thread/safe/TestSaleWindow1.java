package com.xiepanpan.thread.safe;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  两个线程随机售票 使用同步代码块解决线程安全问题
 */
public class TestSaleWindow1 {

    public static void main(String[] args) {
        SaleWindow1 saleWindow1 = new SaleWindow1();
        Runnable target;
        Thread t1 = new Thread(saleWindow1);
        Thread t2 = new Thread(saleWindow1);

        t1.setName("窗口A");
        t2.setName("窗口B");

        t1.start();
        t2.start();
    }
}
