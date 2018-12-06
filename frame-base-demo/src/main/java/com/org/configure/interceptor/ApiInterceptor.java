package com.org.configure.interceptor;

import com.org.base.annotation.SkipVerification;
import com.org.constants.HttpConstants;
import com.org.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Author: WangLin
* @Description: 拦截器
* @Date: 2018/11/21 17:15
*/
public class ApiInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(ApiInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        /*//是否跳过注解
        if(SkipAnnotation(o)){
            return true;
        }
        //内部服务，跳过验证
        if(HttpConstants.Headers.SKIP_VALIDATION.equals(request.getHeader(HttpConstants.Headers.SKIP_VALIDATION))){
            return true;
        }
        if(HttpConstants.Headers.X_TOKEN_VALUE.equals(request.getHeader(HttpConstants.Headers.X_TOKEN_VALUE))){
            //查询Redis,验证用户是否存在
            Object obj = redisUtil.get(request.getHeader(HttpConstants.Headers.X_USER_ID));
            if(null != obj){
                return true;
            }
        }
        log.error("请先登录。");
        return false;*/
        return true;
    }

    /**
     * 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
     * @param request
     * @param response
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理。
     * @param request
     * @param response
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }

    /**
     * 跳过验证
     * @param o
     * @return
     */
    private boolean SkipAnnotation(Object o) {
        HandlerMethod handlerMethod = (HandlerMethod)o;
        SkipVerification skip = handlerMethod.getMethod().getAnnotation(SkipVerification.class);
        if( null != skip ){
            return true;
        }
        return false;
    }
}
