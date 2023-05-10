package com.example.demo.config;

import com.example.demo.auth.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean jwtAuthFilter(JwtProperties jwtProperties, HandlerExceptionResolver handlerExceptionResolver) {
        JwtAuthFilter filter = new JwtAuthFilter(jwtProperties, handlerExceptionResolver);
        FilterRegistrationBean filterBean = new FilterRegistrationBean<>(filter);
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(-103);
        return filterBean;
    }
}