package com.example.jhyangnewthings;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@MapperScan("com.example.jhyangnewthings.*")
@EnableSwagger2
@NacosPropertySource(dataId = "nacosExample", autoRefreshed = true)
public class JhyangNewthingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhyangNewthingsApplication.class, args);
    }

}
