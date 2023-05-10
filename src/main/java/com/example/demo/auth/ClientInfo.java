package com.example.demo.auth;

import lombok.Data;

/**
 * @author Arte
 * @create 2023/5/9.
 */
@Data
public class ClientInfo {

    private String clientId;

    private Integer accessTokenValiditySeconds;

    private String authorities;

    private String authorizedGrantTypes;

    private Integer autoApprove;

    private String clientSecret;

    private Integer refreshTokenValiditySeconds;

    private String registeredRedirectUri;

    private String resourceIds;

    private String scope;

    private Integer scoped;

    private Integer secretRequired;

}
