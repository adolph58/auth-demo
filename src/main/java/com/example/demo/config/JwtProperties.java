package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oauth.jwt")
public class JwtProperties {
    private String secret;
    private int accessTokenExpiredSeconds;
    private int refreshTokenExpiredSeconds;
}
