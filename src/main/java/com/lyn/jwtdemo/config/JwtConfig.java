package com.lyn.jwtdemo.config;

import com.lyn.jwtdemo.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LengYouNuan
 * @create 2021-05-18 下午4:46
 */
@Configuration
public class JwtConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor getJwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJwtInterceptor())
                .addPathPatterns("/**");  //拦截所有请求
    }
}
