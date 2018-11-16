package com.org.base.vo;

import lombok.Data;

import java.util.List;
/**
* @Author: WangLin
* @Description: 分页实体
* @Date: 2018/11/16 15:50
*/
@Data
public class PageVo<T> {
    private Integer count;
    private Integer pageSize;
    private Integer page;
    private List<T> data;
}
