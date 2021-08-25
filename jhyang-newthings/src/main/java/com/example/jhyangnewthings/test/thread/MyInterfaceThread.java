package com.example.jhyangnewthings.test.thread;

/**
 * @author jhYang
 * @date 2019/8/17 0017
 */
public class MyInterfaceThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);

            //休眠一秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
