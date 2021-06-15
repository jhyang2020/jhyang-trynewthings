package com.example.jhyangnewthings.api.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author jhYang
 * @Date 2021/6/7 0007 21:59
 * @Discription todo
 */
@Service
public class OrderService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟订单
     * @param userID
     * @param productId
     * @param num
     */
    public void makeOrder(String userID,String productId,String num){
        String orderID = UUID.randomUUID().toString();
        LOGGER.info("订单生成成功"+userID);

        String exchange = "fanout-order-exchange";
        String queue = "";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchange,routingKey,orderID);

    }

    /**
     *
     * @param userID
     * @param productId
     * @param num
     */
    public void makeOrderDirect(String userID,String productId,String num){
        String orderID = UUID.randomUUID().toString();
        LOGGER.info("订单生成成功"+userID);

        String exchange = "direct-order-exchange";
        String routingKey = "spring-boot-routingKey_B";
        rabbitTemplate.convertAndSend(exchange,routingKey,orderID);
    }

    /**
     *
     * @param userID
     * @param productId
     * @param num
     */
    public void makeOrderDirecttTtl(String userID,String productId,String num){
        String orderID = UUID.randomUUID().toString();
        LOGGER.info("订单生成成功"+userID);

        String exchange = "direct-order-exchange";
        String routingKey = "spring-boot-routingKey_C";
        rabbitTemplate.convertAndSend(exchange,routingKey,orderID);

    }
    public void makeOrderTopic(String userID,String productId,String num){
        String orderID = UUID.randomUUID().toString();
        LOGGER.info("订单生成成功"+userID);

        String exchange = "topic_order_exchage";
        String routingKey = "#.email.#";
        rabbitTemplate.convertAndSend(exchange,routingKey,orderID);

    }

}
