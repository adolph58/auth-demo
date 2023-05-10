package com.example.demo.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthTokenProvider implements AuthTokenProvider {

    @Resource
    private JwtProperties jwtProperties;

    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";
    private static final String TOKEN_TYPE = "bearer";


    @Override
    public Token createAccessToken(IAccount user) {
        Date expired = new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpiredSeconds() * 1000);
        String access_token = createJwtToken(user, expired, false);
        Token token = new Token();
        token.setTokenType(TOKEN_TYPE);
        token.setExpired(expired);
        token.setValue(access_token);
        token.setUser(user);
        return token;
    }

    @Override
    public Token createRefreshToken(IAccount user) {
        Date expired = new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExpiredSeconds() * 1000);
        String refresh_token = createJwtToken(user, expired, true);
        Token token = new Token();
        token.setTokenType(TOKEN_TYPE);
        token.setExpired(expired);
        token.setValue(refresh_token);
        return token;
    }

    @Override
    public Optional<Token> refreshAccessToken(String refreshToken) {
        try {
            Claims claims = decodeToken(refreshToken);
            Boolean isRefreshToken = claims.get("refresh", Boolean.class);
            if (!isRefreshToken) {
                return Optional.empty();
            }
            SecurityClientBo user = AuthContextUtils.decodeToken(refreshToken);
            return Optional.of(createAccessToken(user));
        } catch (JwtException e) {
            
        }
        return Optional.empty();
    }

    private Claims decodeToken(String token) throws JwtException {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token.replaceFirst("Bearer", ""))
                .getBody();
        return claims;
    }

    private String createJwtToken(IAccount user, Date expired, boolean isRefresh) {
        String json = JSON.toJSONString(user);
        JSONObject userProperties = JSON.parseObject(json);
        userProperties.put("refresh", isRefresh);
        String token = Jwts.builder()
                .setClaims(userProperties)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
        return token;
    }
}
