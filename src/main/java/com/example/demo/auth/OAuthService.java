package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AccessTokenService 对于贫血模型，通过Service来封装业务逻辑
 *
 * @author DaLiu
 * @since 2022-06-30
 */
@Service
public class OAuthService {

    @Autowired
    private AuthTokenProvider authTokenProvider;

    public Token createAccessToken(IAccount user) {
        return authTokenProvider.createAccessToken(user);
    }

    public Token createRefreshToken(IAccount user) {
        return authTokenProvider.createRefreshToken(user);
    }

    public Optional<Token> refreshAccessToken(String refreshToken) {
        Optional<Token> accessToken = authTokenProvider.refreshAccessToken(refreshToken);
        return accessToken;
    }
}
