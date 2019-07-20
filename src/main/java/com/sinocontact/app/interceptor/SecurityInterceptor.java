package com.sinocontact.app.interceptor;

import com.sinocontact.app.common.CommonConstant;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 * @author todd
 * @since 2019/1/21
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object sessionObject = session.getAttribute(CommonConstant.SESSION_KEY);
        if (sessionObject !=null){
            request.setAttribute(CommonConstant.SESSION_KEY,sessionObject);
            return true;
        }
        String sPath=request.getContextPath();
        String url= sPath+"/user/login";
        response.sendRedirect(url);
        return false;
    }
}
