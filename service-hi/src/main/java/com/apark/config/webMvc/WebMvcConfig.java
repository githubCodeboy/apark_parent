package com.apark.config.webMvc;


import com.apark.interceptor.CROSHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CROSHandlerInterceptor()).addPathPatterns("/**");
    }



}
