package com.org.configure.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.org.util.DruidConfigTools;

/**
* @Author: WangLin
* @Description: 解密数据库用户密码
* @Date: 2018/11/27 11:44
*/
public class SecurityDateSource extends DruidDataSource {

    @Override
    public void setUsername(String username) {
        try {
            username = DruidConfigTools.decryptCode(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        try {
            password = DruidConfigTools.decryptCode(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setPassword(password);
    }
}
