package com.example.jhyangnewthings.constructor.interceptordeal;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(new RequestInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.ttf"
        );
    }
}
