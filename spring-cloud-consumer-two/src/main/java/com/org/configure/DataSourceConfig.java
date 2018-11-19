package com.org.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
* @Author: WangLin
* @Description:  多数据源配置，定义mysql、oracle.
* @Date: 2018/11/15 14:38
*/
@Configuration
@MapperScan(basePackages = "com.org.mapper")
public class DataSourceConfig {

    private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("jdbc1.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("jdbc1.datasource.username"));
        dataSource.setPassword(env.getProperty("jdbc1.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc1.datasource.driverClassName"));
        setCommonConfiguration(dataSource);
        return dataSource;
    }

    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("jdbc2.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("jdbc2.datasource.username"));
        dataSource.setPassword(env.getProperty("jdbc2.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc2.datasource.driverClassName"));
        setCommonConfiguration(dataSource);
        return dataSource;
    }

    /**
     * 创建动态数据源，将上面创建的数据源交给动态数据源管理(即放到抽象类AbstractRoutingDataSource中的targetDataSources管理)
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
            @Qualifier("oracleDataSource") DataSource oracleDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.mysqlDb, mysqlDataSource);
        targetDataSources.put(DatabaseType.oracleDb, oracleDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为mysqlDataSource
        dataSource.setDefaultTargetDataSource(mysqlDataSource);
        return dataSource;
    }

    /**
     *将动态数据源交给mybatis的SqlSessionFactoryBean，并添加PageHelper分页插件。
     *因为要切换数据源，必须要把PageHelper的autoRuntimeDialect属性设置为true才能在不同类新的数据源切换时，
     *使用不同数据源的分页方式。
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
            @Qualifier("oracleDataSource") DataSource oracleDataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(this.dataSource(mysqlDataSource, oracleDataSource));
        //添加XML目录
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //设置公共配置
    private void setCommonConfiguration(DruidDataSource datasource){
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
            log.error("",e);
            e.printStackTrace();
        }
        datasource.setConnectionProperties(env.getProperty("datasource.connectionProperties"));
    }
}
