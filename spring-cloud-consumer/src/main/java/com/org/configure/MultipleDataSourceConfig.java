package com.org.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
* @Author: WangLin
* @Description:  多数据源配置，定义mysql、oracle.
* @Date: 2018/11/15 14:38
*/
@Configuration
public class MultipleDataSourceConfig {

    private Logger log = LoggerFactory.getLogger(MultipleDataSourceConfig.class);

    @Autowired
    private Environment env;

    @Bean(name="druidDataSource1")
    public DruidDataSource getDruidDataSource1(){
        return new DruidDataSource();
    }

    @Bean(name="druidDataSource2")
    public DruidDataSource getDruidDataSource2(){
        return new DruidDataSource();
    }

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary // 定义主数据源mysql
    public DataSource primaryDataSource(@Qualifier("druidDataSource1")DruidDataSource dataSource) {
        dataSource.setUrl(env.getProperty("jdbc1.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("jdbc1.datasource.username"));
        dataSource.setPassword(env.getProperty("jdbc1.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc1.datasource.driverClassName"));
        setCommonConfiguration(dataSource);
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    public DataSource secondaryDataSource(@Qualifier("druidDataSource2")DruidDataSource dataSource) {
        dataSource.setUrl(env.getProperty("jdbc2.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("jdbc2.datasource.username"));
        dataSource.setPassword(env.getProperty("jdbc2.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc2.datasource.driverClassName"));
        setCommonConfiguration(dataSource);
        return dataSource;
    }

    private void setCommonConfiguration(DruidDataSource datasource){
        //datasource.setDbType("oracle.jdbc.xa.client.OracleXADataSource");
        datasource.setInitialSize(Integer.valueOf(env.getProperty("datasource.initialSize")));
        datasource.setMinIdle(Integer.valueOf(env.getProperty("datasource.minIdle")));
        datasource.setMaxActive(Integer.valueOf(env.getProperty("datasource.maxActive")));
        datasource.setMaxWait(Integer.valueOf(env.getProperty("datasource.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("datasource.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("datasource.minEvictableIdleTimeMillis")));
        //开启8小时不断连接
        datasource.setValidationQuery(env.getProperty("datasource.validationQuery"));
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        //打开PSCache，并且指定每个连接上PSCache的大小
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("datasource.maxPoolPreparedStatementPerConnectionSize")));
        try {
            datasource.setFilters(env.getProperty("datasource.filters"));
        } catch (SQLException e) {
            log.error("");
            e.printStackTrace();
        }
        datasource.setConnectionProperties(env.getProperty("datasource.connectionProperties"));
    }
}
