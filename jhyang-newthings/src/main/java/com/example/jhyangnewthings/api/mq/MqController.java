package com.example.jhyangnewthings.api.mq;

import com.example.jhyangnewthings.api.mq.producer.OrderService;
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
@Api(value = "生产服务")
public class MqController {

    @Autowired
    private OrderService orderService;

    /**
     * @param str
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqProduct")
    public Response testMqProduct(String str){
        orderService.makeOrder(str,"14","14");
        return Response.ok("testMqProduct！");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqProductDirect")
    public Response testMqProductDirect(String str){
        orderService.makeOrderDirect(str,"14","14");
        return Response.ok("testMqProductDirect！");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqProductDirectTtl")
    public Response testMqProductDirectTtl(String str){
        orderService.makeOrderDirecttTtl(str,"14","14");
        return Response.ok("test makeOrderDirecttTtl！");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/testMqProductTopic")
    public Response testMqProductTopic(String str){
        orderService.makeOrderTopic(str,"14","14");
        return Response.ok("testMqProductDirect！");
    }

}
