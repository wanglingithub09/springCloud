package com.org.base.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
* @Author: WangLin
* @Description: 分页实体
* @Date: 2018/11/16 15:50
*/
@Data
public class PageVo<T> {
    /**
     * 总数
     */
    private Integer count;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 页面索引
     */
    private Integer page;
    /**
     * 数据
     */
    private List<T> data;

    public PageVo(){
        this.data = new ArrayList<>();
    }

    public PageVo(List<T> data){
        this.data = data;
    }
}
