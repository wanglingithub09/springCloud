package com.org.base.aspect;

import com.github.pagehelper.PageHelper;
import com.org.base.vo.PageQuery;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class EnablePagingAspect {

    @Pointcut("@annotation(com.org.base.Annotation.EnablePaging)")
    public void pointcut(){

    }

    public static String PAGE_STR = "page";
    public static String PAGE_SIZE_STR = "size";

    @Before("pointcut()")
    public void doAround(JoinPoint point){
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++){
            if(args[i] instanceof PageQuery){
                PageQuery pageQuery = (PageQuery)args[i];
                if (pageQuery.isPaging()) {
                    PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
                    return;
                }
                return;
            }
            if(args[i] instanceof Map){
                Map map = (Map)args[i];
                if(map.containsKey(PAGE_STR) && map.containsKey(PAGE_SIZE_STR)){
                    PageHelper.startPage(Integer.valueOf(map.get(PAGE_STR).toString()),
                            Integer.valueOf(map.get(PAGE_SIZE_STR).toString()));
                }
            }
        }
    }

}
