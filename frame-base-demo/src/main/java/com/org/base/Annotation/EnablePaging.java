package com.org.base.Annotation;

import java.lang.annotation.*;
/**
* @Author: WangLin
* @Description: 分页注解
* @Date: 2018/11/16 16:00
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnablePaging {

}
