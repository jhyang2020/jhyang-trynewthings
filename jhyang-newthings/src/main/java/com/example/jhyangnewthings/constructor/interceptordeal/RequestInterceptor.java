package com.example.jhyangnewthings.constructor.interceptordeal;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jhYang
 * @Description 请求拦截
 * @Date 2020/3/28
 */

public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String user = request.getParameter("user");
//        if(StringUtils.isEmpty(user)){
//            request.setAttribute("msg","没有访问权限！");
//            request.getRequestDispatcher("/index.html").forward(request,response);
//            return false;
//        } else if(AllowedUsers.list.contains(user)){
//            return true;
//        } else{
//            return false;
//        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
