package com.example.demo.config;

/**
 * 允许直接访问的路径定义
 *
 * @author hp
 * @since 2020-12-21
 */
public class AccessPaths {

    /**
     * swagger ui
     */
    public static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**", "/swagger-ui.html",
            "/v2/api-docs", "/webjars/**", "/oauth/token"
    };

    /**
     * 静态资源
     */
    public static final String[] STATIC_RESOURCE = {
            ".html", ".js", ".css", ".jpg", ".png"
    };

}
