package com.example.jhyangnewthings.constructor.datasource.rootdatasource;
import com.example.jhyangnewthings.common.utils.SpringContextUtil;
import com.example.jhyangnewthings.constructor.datasource.constant.DatasourceConstant;
import com.example.jhyangnewthings.constructor.datasource.interceptor.SqlPrintInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "com.fiberhome.uqbing.*")
public class MyBatisAndDataSourceConfiguration {

    private static Logger log = LoggerFactory.getLogger(MyBatisAndDataSourceConfiguration.class);

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Autowired
    @Qualifier("logPgDataSource")
    private DataSource logPgDataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.info("--------------------  sqlSessionFactory init ---------------------");
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        //设置经过路由的数据源
        sessionFactoryBean.setDataSource(roundRobinDataSouceProxy());

        //设置mybatis配置文件位置
        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        sessionFactoryBean.setMapperLocations(resources);
        //添加分页插件、打印sql插件
        Interceptor[] plugins = new Interceptor[]{pageHelper(), new SqlPrintInterceptor()};
        sessionFactoryBean.setPlugins(plugins);
        return sessionFactoryBean.getObject();
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    /**
     * 把所有数据库都放在路由中
     *
     * @return
     */
    @Bean(name = "roundRobinDataSouceProxy")
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        //注册所有库
        targetDataSources.put(DatasourceConstant.MYSQL_DATA_SOURCE, mysqlDataSource);
        targetDataSources.put(DatasourceConstant.LOG_PG_DATA_SOURCE,logPgDataSource);

        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
            //动态切换数据源
            @Override
            protected Object determineCurrentLookupKey() {
                String typeKey = DataSourceContextHolder.getDatabase();

                if (typeKey.equals(DatasourceConstant.MYSQL_DATA_SOURCE)) {
                    return DatasourceConstant.MYSQL_DATA_SOURCE;
                } else{
                    return DatasourceConstant.LOG_PG_DATA_SOURCE;
                }

            }
        };
        proxy.setDefaultTargetDataSource(logPgDataSource);//默认库
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager((DataSource) SpringContextUtil.getBean("roundRobinDataSouceProxy"));
    }
}
