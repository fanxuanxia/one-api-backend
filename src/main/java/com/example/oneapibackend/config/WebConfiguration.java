package com.example.oneapibackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
////     token鉴权实现方式
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
////    构造方法
//    public WebConfiguration(TokenInterceptor tokenInterceptor){
//        this.tokenInterceptor = tokenInterceptor;
//    }
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
//        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
//        configurer.setDefaultTimeout(30000);
//    }
//


    @Autowired
    private HandlerInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/user/register");  //登录
        excludePath.add("/user/login");     //注册
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源

        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
