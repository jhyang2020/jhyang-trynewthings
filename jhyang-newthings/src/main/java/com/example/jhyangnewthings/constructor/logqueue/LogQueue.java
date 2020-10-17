package com.example.jhyangnewthings.constructor.logqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.jhyangnewthings.common.constant.NumConstant.IntNum.NUM_1;
/**
 * @Author jhYang
 * @Date 2020/4/21 0021 13:48
 * @Discription todo
 */

/**
 * 操作日志的队列
 */
public class LogQueue {
    private static Logger log = LoggerFactory.getLogger(LogQueue.class);

    //队列大小
    public static final int QUEUE_MAX_SIZE    = 100;

    /**
     * 线程池参数
     */
    static int corePoolSize = 100;
    static long keepActiveTime = 200;
    static int maximumPoolSize = 300;
    static TimeUnit timeUnit = TimeUnit.SECONDS;

    public static ThreadPoolExecutor executor = null;
    public static BlockingQueue<LogBean> blockingQueue = null;
    static{
        blockingQueue = new LinkedBlockingQueue<LogBean>(QUEUE_MAX_SIZE);
        executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepActiveTime,timeUnit,new LinkedBlockingQueue<Runnable>(100));
    }

    /**
     * 消息入队
     * @param logBean
     * @return
     */
    public synchronized static void push(LogBean logBean) throws Exception {
        //队列已满时,会阻塞队列,直到未满
        blockingQueue.put(logBean);
    }
    /**
     * 消息出队
     * @return
     */
    public synchronized static LogBean take() {
        LogBean result = null;
        try {
            result = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized static LogBean poll() {
        LogBean result = null;
        try {
            result = blockingQueue.poll(NUM_1,TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 获取队列大小
     * @return
     */
    public static int size() {
        return blockingQueue.size();
    }

}