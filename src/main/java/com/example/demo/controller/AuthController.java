package com.example.demo.controller;

import com.example.demo.auth.*;
import com.example.demo.handle.BusinessException;
import com.example.demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 登录认证接口示例
 *
 * Topss采用SSO单点登录方式，该接口只是临时提供认证的demo
 *
 * @author Daliu
 * @since 2023-04-20
 */
@Slf4j
@RestController
@Api(tags = {"WEB-登录接口"})
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private OAuthService oauthService;

    /**
     * 认证获取访问令牌
     * 目前只提供用户名+密码模式的实现
     *
     * @param
     * @return 操作结果
     */
    @ApiOperation(value = "001-认证获取访问令牌", notes = "")
    @PostMapping(path = "/token", name = "创建AccessToken")
    public Result<AccessToken> token(
            @ApiParam(value = "授权模式", allowableValues="password, refresh_token, client_credentials", required = true)
            @RequestParam("grant_type") String grantType,
            @ApiParam(value = "客户端ID", required = false)
            @RequestParam(value = "client_id", required = false) String clientId,
            @ApiParam(value = "客户端密钥", required = false)
            @RequestParam(value = "client_secret", required = false) String clientSecret,
            @ApiParam(value = "刷新令牌：refresh_token授权模式必填")
            @RequestParam(value = "refresh_token", required = false) String refreshToken,
            @ApiParam(value = "用户账号：password模式必填")
            @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "用户密码：password模式必填")
            @RequestParam(value = "password", required = false) String password) {

        switch (GrantType.fromCode(grantType)) {
            case PASSWORD:
                return Result.success(createToken(username, password));
            case REFRESH_TOKEN:
                return Result.success(refreshAccessToken(refreshToken));
            case CLIENT_CREDENTIALS:
                return Result.success(createClientToken(clientId, clientSecret));
            default:
                throw new UnsupportedOperationException("不支持的授权类型");
        }
    }

    private IAccount authenticate(String username, String password) {
        // TODO: 验证用户名密码
        // User user = userRepository.findByUserCode(username);
        // if (user == null) {
        //     throw new ServiceException(Code.newInstance(401, "无效凭证", "认证凭证无效"));
        // }
        // if (!passwordEncoder.matches(password, user.get().getPassword())) {
        //     throw new ServiceException(Code.newInstance(401, "无效凭证", "认证凭证无效"));
        // }

        // TODO: 按需求构建用户信息，生成到JWT token
        SecurityClientBo user = new SecurityClientBo();
        user.setId(2023L);
        user.setClientId("0567890112");
        Map<String, Object> extension = new HashMap<>();
        extension.put("username", "测试用户");
        user.setExtension(extension);
        user.setInvalid(Boolean.FALSE);
        return user;
    }

    private IAccount authenticateClient(String clientId, String clientSecret) {
        // TODO: 验证客户端信息
        // User user = userRepository.findByUserCode(username);
        // if (user == null) {
        //     throw new ServiceException(Code.newInstance(401, "无效凭证", "认证凭证无效"));
        // }
        // if (!passwordEncoder.matches(password, user.get().getPassword())) {
        //     throw new ServiceException(Code.newInstance(401, "无效凭证", "认证凭证无效"));
        // }

        // TODO: 按需求构建用户信息，生成到JWT token
        SecurityClientBo user = new SecurityClientBo();
        user.setId(202305L);
        user.setClientId("ew_client");
        Map<String, Object> extension = new HashMap<>();
        extension.put("client", "测试客户端");
        user.setExtension(extension);
        user.setInvalid(Boolean.FALSE);
        return user;
    }

    private AccessToken createToken(String username, String password) {
        IAccount user = authenticate(username, password);
        Token accessToken = oauthService.createAccessToken(user);
        Token refreshToken = oauthService.createRefreshToken(user);
        RefreshToken refreshTokenBo = new RefreshToken(refreshToken.getValue());
        return toAccessTokenBo(accessToken, refreshTokenBo);
    }

    private AccessToken createClientToken(String clientId, String clientSecret) {
        IAccount user = authenticateClient(clientId, clientSecret);
        Token accessToken = oauthService.createAccessToken(user);
        Token refreshToken = oauthService.createRefreshToken(user);
        RefreshToken refreshTokenBo = new RefreshToken(refreshToken.getValue());
        return toAccessTokenBo(accessToken, refreshTokenBo);
    }

    private AccessToken refreshAccessToken(String refreshToken) {
        Optional<Token> accessToken = oauthService.refreshAccessToken(refreshToken);
        if (!accessToken.isPresent()) {
            throw new BusinessException(403, "无效凭证", "当前凭证已失效");
        }
        return toAccessTokenBo(accessToken.get(), new RefreshToken(refreshToken));
    }

    private AccessToken toAccessTokenBo(Token accessToken, RefreshToken refreshToken) {
        AccessToken tokenBO = new AccessToken();
        RefreshToken refreshTokenBO = new RefreshToken(refreshToken.getValue());
        tokenBO.setAccess_token(accessToken.getValue());
        tokenBO.setExpired(accessToken.getExpired());
        tokenBO.setRefreshToken(refreshTokenBO);
        tokenBO.setUsername(accessToken.getUser().getName());
        return tokenBO;
    }
}
