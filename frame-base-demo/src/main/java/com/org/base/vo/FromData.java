package com.org.base.vo;

import lombok.Data;

import java.util.Map;
/**
* @Author: WangLin
* @Description: 入参实体
* @Date: 2018/11/19 9:38
*/
@Data
public class FromData<T> extends PageQuery {
    /**
     * 实体参数
     */
    T bo;

    /**
     * 其它参数
     */
    Map<String,?> other;

}
