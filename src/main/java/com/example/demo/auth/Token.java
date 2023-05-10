package com.example.demo.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Token {
    IAccount user;

    /**
     * 令牌值
     */
    private String value;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 过期时间
     */
    private Date expired;
}