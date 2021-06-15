package com.learn.rabbitmq.springbootorderrabbitmqproducer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author jhYang
 * @Date 2021/6/7 0007 22:19
 * @Discription todo
 */
@Configuration
public class RabbitConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static final String EXCHANGE_A = "fanout-order-exchange";
    public static final String EXCHANGE_B = "direct-pay-exchange";
    public static final String EXCHANGE_C = "fanout-vip-exchange";


    public static final String QUEUE_A = "sms.fanout.queue";
    public static final String QUEUE_B = "duanxin.fanout.queue";
    public static final String QUEUE_C = "email.fanout.queue";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    /**
     * 交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutOrderExchange(){
        return new FanoutExchange(EXCHANGE_A, true, false);
    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue smsQueue(){
        return new Queue(QUEUE_A,true);
    }
    @Bean
    public Queue duanxinQueue(){
        return new Queue(QUEUE_B,true);
    }
    @Bean
    public Queue emailQueue(){
        return new Queue(QUEUE_C,true);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutOrderExchange());
    }
    @Bean
    public Binding duanxinBinding(){
        return BindingBuilder.bind(duanxinQueue()).to(fanoutOrderExchange());
    }
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutOrderExchange());
    }




}
