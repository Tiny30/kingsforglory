package com.sqy.kingsforglory.glory.interceptor;

import com.sqy.kingsforglory.glory.config.PageConfig;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //初始化page插件
        PageConfig.init(request);
        return true;
    }
}
