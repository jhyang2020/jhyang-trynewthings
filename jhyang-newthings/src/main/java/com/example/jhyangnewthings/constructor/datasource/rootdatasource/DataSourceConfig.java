package com.example.jhyangnewthings.constructor.datasource.rootdatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring")
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 获取配置文件中的数据源
     */
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceOfDruidDataSource;


    /**
     * 获取配置文件中的PG库
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "logPgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.logpgdatasource")
    public DataSource logPgDataSource() throws Exception {
        logger.info("---------------------logPgDataSource init ----------------------");
        return DataSourceBuilder.create().type(dataSourceOfDruidDataSource).build();
    }

    /**
     * 获取配置文件中的mysql库
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "mysqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysqldatasource")
    public DataSource messagePushDataSource() throws Exception {
        logger.info("----------------------messagePushDataSource init ----------------------");
        return DataSourceBuilder.create().type(dataSourceOfDruidDataSource).build();
    }


}
