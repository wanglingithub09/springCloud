package com.org.configure.datasource;
/**
* @Author: WangLin
* @Description: 通过线程来设置当前数据源
* @Date: 2018/11/20 10:15
*/
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final DatabaseType DEFAULT_DS = DatabaseType.mysqlDb;

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void clearDatabaseType() {
        contextHolder.remove();
    }

}
