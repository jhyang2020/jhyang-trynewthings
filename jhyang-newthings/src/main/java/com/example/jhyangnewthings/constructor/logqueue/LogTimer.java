package com.example.jhyangnewthings.constructor.logqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author jhYang
 * @Date 2020/4/21 0021 14:07
 * @Discription todo  消费者
 */
//@Component
@EnableScheduling
public class LogTimer {
    private static Logger log = LoggerFactory.getLogger(LogTimer.class);

    @Scheduled(cron = "* */1 * * * ? ")  //每1小时执行一次
    public void saveLog() {
        while (true) {
            //阻塞一个小时
            LogBean entity = LogQueue.poll();
            log.info("【LogTimer】消费过程开始...");
            //执行队列中的任务
            if (null != entity) {
                LogQueue.executor.submit(new LogThread(entity));
            } else {
                log.info("【LogTimer】获取为空，跳出循环");
                break;
            }
        }
    }

}