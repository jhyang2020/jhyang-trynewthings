package com.example.jhyangnewthings.constructor.logqueue;
import org.springframework.stereotype.Component;

/**
 * 消费者线程
 * 日志线程类,将日志保存到队列中
 */
@Component
public class LogThread implements Runnable {

    private LogBean logBean;

    public LogThread() {
    }

    public  LogThread(LogBean logBean) {
        this.logBean = logBean;
    }

    @Override
    public void run() {
        try {
            System.out.println("【LogThread】消费的过程处理:"+logBean.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
