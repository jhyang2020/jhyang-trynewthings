package com.example.jhyangnewthings.api.userlog.controller;

import com.example.jhyangnewthings.common.bean.Response;
import com.example.jhyangnewthings.api.userlog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/27
 */
@RestController
@RequestMapping("/log")
@Api(value = "后台日志运营服务")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 获得日志总条数
     * @param user
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "关键词", dataType = "String", required = true)
    })
    @PostMapping("/getTotalNumOfLog")
    public Response test(String user) {
        try {

            System.out.println(user);
            List<Map<String, Object>> totalNumOfLog = logService.getTotalNumOfLog();
            return Response.ok(totalNumOfLog);
        } catch (Exception e) {
            return Response.ok("测试出错...");
        }
    }

}
