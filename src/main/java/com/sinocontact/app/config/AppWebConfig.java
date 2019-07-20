package com.sinocontact.app.config;

import com.sinocontact.app.interceptor.SecurityInterceptor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppWebConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger= Logger.getLogger(AppWebConfig.class);
    /**
     *添加资源路径
     */
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry){
        //添加静态资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classPath/static/");
        //添加其他资源路径
        //....
    }

    /**
     * 添加拦截器
     */

     public void addInterceptors(InterceptorRegistry registry){
            //添加一个登录拦截器
             addSecurityInterceptors(registry);
     }

    /**
     * 添加登录拦截器
     * @author todd
     * @since 2019/1/21
     */
    private void addSecurityInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(new SecurityInterceptor());

        //登录相关
        addInterceptor.excludePathPatterns("/user/**");


        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

}
