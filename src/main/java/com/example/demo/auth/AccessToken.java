package com.example.demo.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class AccessToken {

    @Setter
    private String access_token;
    @Setter
    private RefreshToken refreshToken;
    @Setter
    private Date expired;
    @Getter@Setter
    private String username;

    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    public Set<String> getScope() {
        return null;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return "bearer";
    }

    public boolean isExpired() {
        return expired.getTime() < System.currentTimeMillis();
    }

    public Date getExpiration() {
        return expired;
    }

    public int getExpiresIn() {
        return (int)(expired.getTime() - System.currentTimeMillis()) / 1000;
    }

    public String getValue() {
        return access_token;
    }
}
