package com.helltab.threads;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Topic 多线程实例
 * @author helltab
 * @version 1.0
 * @date 2021/6/21 21:20
 */
public class Start01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("真的");
        Run1 run1 = new Run1();
        Thread thread = new Thread(run1);
        thread.start();
        for (int i = 0; i < 100; i++) {
            TimeUnit.SECONDS.sleep(1);
            if(i==10) {
                run1.stop();
                break;
            }
        }

        System.out.println(thread.getState());
    }
}
class Run1 implements Runnable{

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (this.flag) {
            System.out.println(Thread.currentThread().getState() + "running: " + i++);
        }
    }
    public void stop() {
        this.flag = false;
    }

}
