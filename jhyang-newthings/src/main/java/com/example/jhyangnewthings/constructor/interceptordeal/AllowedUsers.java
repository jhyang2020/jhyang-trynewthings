package com.example.jhyangnewthings.constructor.interceptordeal;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/28
 */
@Component
@PropertySource(value = "classpath:config/interceptor-user.properties")
@ConfigurationProperties(prefix = "user")
public class AllowedUsers {
    public static  List<String> list = Lists.newArrayList();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
