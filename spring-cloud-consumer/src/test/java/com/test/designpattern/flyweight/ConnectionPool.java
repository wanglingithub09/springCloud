package com.test.designpattern.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    Vector<Connection> pool;

    /** 公有属性* */
    private String url = "jdbc:mysql://10.0.55.159:3306/mango_dev?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;useSSL=false&amp;pinGlobalTxToPhysicalConnection=true&amp;allowMultiQueries=true&amp;failOverReadOnly=false";
    private String username = "root";
    private String password = "Mango123";
    private String diverClassName = "com.mysql.jdbc.Driver";

    private int poolSize = 10;
    Connection connection;

    /*构造方法，初始化连接池*/
    public ConnectionPool(){
        pool = new Vector<>(poolSize);

        for(int i=0; i<poolSize; i++){
            try {
                Class.forName(diverClassName);
                connection = DriverManager.getConnection(url,username,password);
                pool.add(connection);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /* 返回连接到连接池 */
    public synchronized void release(){
        pool.add(connection);
    }

    /* 返回连接池中的一个数据库连接 */
    public synchronized Connection getConnection(){
        if(pool.size() > 0){
            connection = pool.get(0);
            pool.remove(connection);
            return connection;
        }
        return null;
    }

}
