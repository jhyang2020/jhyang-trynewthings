package com.example.jhyangnewthings.constructor.datasource.annotation;
import com.example.jhyangnewthings.constructor.datasource.constant.DatasourceConstant;

import java.lang.annotation.*;

/**
 * 动态路由数据源注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TargetDatasource {
    String value() default DatasourceConstant.LOG_PG_DATA_SOURCE;
}
