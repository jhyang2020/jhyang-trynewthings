package com.example.jhyangnewthings.api.mq.consumer;

import com.example.jhyangnewthings.api.mq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author jhYang
 * @Date 2021/6/2 0002 20:53
 * @Discription todo
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiverC_two {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RabbitHandler
    public void process(String content) {
        logger.info("处理器two接收处理队列A当中的消息： " + content);
    }

}