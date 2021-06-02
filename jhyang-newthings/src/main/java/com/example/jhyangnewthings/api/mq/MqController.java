package com.example.jhyangnewthings.api.mq;

import com.example.jhyangnewthings.api.mq.consumer.MsgReceiver;
import com.example.jhyangnewthings.api.mq.producer.MsgProducer;
import com.example.jhyangnewthings.common.bean.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jhYang
 * @Date 2021/6/2 0002 20:59
 * @Discription todo
 */
@RestController
@RequestMapping("/mq")
@Api(value = "消息队列服务")
public class MqController {

    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private MsgReceiver msgReceiver;

    /**
     * @param str
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqProduct")
    public Response testMqProduct(String str){
        msgProducer.sendMsg(str);
        return Response.ok("testMqProduct！");
    }

    /**
     * @param str
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqConsumer")
    public Response testMqConsumer(String str){
        msgReceiver.processA(str);
        return Response.ok("testMqConsumer!");
    }
}
