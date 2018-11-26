package com.org.base.annotation;

import java.lang.annotation.*;

/**
* @Author: WangLin
* @Description: 跳过验证
* @Date: 2018/11/23 15:54
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipVerification {
}
