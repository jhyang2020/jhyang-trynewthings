package com.example.jhyangnewthings.constructor.aop;

import com.example.jhyangnewthings.constructor.datasource.annotation.TargetDatasource;
import com.example.jhyangnewthings.constructor.datasource.constant.DatasourceConstant;
import com.example.jhyangnewthings.constructor.datasource.rootdatasource.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
//注解驱动切面
public class DataSourceAopInService {

    private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);

    @Before("@annotation(targetDatasource)")
    public void doBefore(TargetDatasource targetDatasource) {
        String datasourceKey = targetDatasource.value();
        if (DatasourceConstant.LOG_PG_DATA_SOURCE.equals(datasourceKey)) {
            DataSourceContextHolder.setDatabase(DatasourceConstant.LOG_PG_DATA_SOURCE);
            log.info("数据库为pgSQL日志库!!!");
        } else if (DatasourceConstant.MYSQL_DATA_SOURCE.equals(datasourceKey)){
            DataSourceContextHolder.setDatabase(DatasourceConstant.MYSQL_DATA_SOURCE);
            log.info("数据库mysql库!!!");
        }
    }

    @After("@annotation(targetDatasource)")
    public void doAfter(TargetDatasource targetDatasource) {
        log.info(String.format("当前数据源 %s 执行清理方法", targetDatasource.value()));
        DataSourceContextHolder.clear();
    }

}
