package com.org.base.vo;

import lombok.Data;

/**
* @Author: WangLin
* @Description: 分页查询抽象类
* @Date: 2018/11/16 15:54
*/
@Data
public abstract class PageQuery {
    /**
     * 页面索引
     */
    protected int page;
    /**
     * 每页大小
     */
    protected  int size;
    /**
     * 是否分页，默认分页
     */
    protected boolean paging = true;

}
