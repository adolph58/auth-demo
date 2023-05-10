package com.example.demo.auth;

import java.util.Optional;

public interface AuthTokenProvider {

    Token createAccessToken(IAccount user);

    Token createRefreshToken(IAccount user);

    Optional<Token> refreshAccessToken(String refreshToken);
}
