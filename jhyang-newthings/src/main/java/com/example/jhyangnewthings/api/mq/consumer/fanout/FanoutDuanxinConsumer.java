package com.example.jhyangnewthings.api.mq.consumer.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author jhYang
 * @Date 2021/6/12 0012 13:16
 * @Discription todo
 */
@Component
@RabbitListener(queues = {"duanxin.fanout.queue"})
public class FanoutDuanxinConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void receiveMessage(String msg){
        LOGGER.info("接收到了短信消息："+msg);
    }

}
