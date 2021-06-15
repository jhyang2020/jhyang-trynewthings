package com.example.jhyangnewthings.api.mq.config;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author jhYang
 * @Date 2021/6/13 0013 14:58
 * @Discription todo
 */
@Configuration
public class DirectRabbitConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static final String DIRECT_EXCHANGE_A = "direct-order-exchange";

    public static final String QUEUE_A = "sms.direct.queue";
    public static final String QUEUE_B = "duanxin.direct.queue";
    public static final String QUEUE_C = "email.direct.ttl.queue";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    /**
     * 交换机
     * @return
     */
    @Bean
    public DirectExchange directOrderExchange(){
        return new DirectExchange(DIRECT_EXCHANGE_A, true, false);
    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue smsDirectQueue(){
        return new Queue(QUEUE_A,true);
    }
    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue(QUEUE_B,true);
    }
    @Bean
    public Queue emailDirectQueue(){
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-message-ttl", 5000);

//        //死信队列，将过期的消息转移到死信队列中
//        args.put("x-dead-letter-exchange", "交换机名称");
//        args.put("x-dead-letter-routing-key","key");

        return new Queue(QUEUE_C,true,false,false,args);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directOrderExchange()).with(ROUTINGKEY_A);
    }
    @Bean
    public Binding duanxinDirectBinding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directOrderExchange()).with(ROUTINGKEY_B);
    }
    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directOrderExchange()).with(ROUTINGKEY_C);
    }



}
