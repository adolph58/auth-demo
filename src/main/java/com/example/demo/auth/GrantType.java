package com.example.demo.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 认证授权的类型
 */
public enum GrantType {

    PASSWORD("password", "密码模式"),
    REFRESH_TOKEN("refresh_token", "刷新令牌模式"),
    CLIENT_CREDENTIALS("client_credentials", "客户端密钥模式"),
    AUTHORIZATION_CODE("authorization_code", "授权码模式");

    protected String code;
    protected String description;

    GrantType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonProperty
    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    @JsonCreator
    public static GrantType fromCode(String code) {
        for(GrantType type : GrantType.values()) {
            if (Objects.equals(type.code, code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("GrantType code was illegal.");
    }
}
