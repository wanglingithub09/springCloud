package com.org.configure.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @Author: WangLin
* @Description: 继承抽象类AbstractRoutingDataSource实现抽象方法——选择切换到哪个数据源的方法
* @Date: 2018/11/16 15:28
*/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDatabaseType();
    }
}
