package com.example.jhyangnewthings.api.mq.consumer.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author jhYang
 * @Date 2021/6/12 0012 13:15
 * @Discription todo
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchage",type = ExchangeTypes.DIRECT),
        key = "#.email.#"
))
public class TopicEmailConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void receiveMessage(String msg){
        LOGGER.info("接收到了邮件消息："+msg);
    }
}
