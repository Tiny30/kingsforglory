package com.sqy.kingsforglory.glory.config;

import com.sqy.kingsforglory.glory.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        //添加token过滤器
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")//过滤所有请求 排除 登录和注册请求
                .excludePathPatterns("/user/login", "/user/register");
//        registry.addInterceptor(new PageInterceptor())
//                .addPathPatterns("/**");

    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
//        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
//        /// 设置总上传数据总大小10M
//        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
//        return factory.createMultipartConfig();
//    }
}
