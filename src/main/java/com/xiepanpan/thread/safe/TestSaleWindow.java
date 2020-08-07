package com.xiepanpan.thread.safe;

/**
 * @author: xiepanpan
 * @Date: 2020/8/3
 * @Description:  两个线程随机售票 出现线程安全问题
 */
public class TestSaleWindow {

    public static void main(String[] args) {
        SaleWindow saleWindow = new SaleWindow();
        Runnable target;
        Thread t1 = new Thread(saleWindow);
        Thread t2 = new Thread(saleWindow);

        t1.setName("窗口A");
        t2.setName("窗口B");

        t1.start();
        t2.start();
    }
}
