package com.example.jhyangnewthings.api.mq.consumer.direct;

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
@RabbitListener(queues = {"email.direct.ttl.queue"})
public class DirectEmailConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void receiveMessage(String msg){
        LOGGER.info("接收到了邮件消息："+msg);
    }

}
