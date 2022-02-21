package com.adrianmensing.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class TokenAuthorizationByCookieConfiguration implements WebMvcConfigurer {

    @Resource
    private AuthCookieInterceptor authCookieInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCookieInterceptor)
                .addPathPatterns("/**");
    }
}
