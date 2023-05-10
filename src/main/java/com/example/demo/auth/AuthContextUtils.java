package com.example.demo.auth;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class AuthContextUtils {
    private static final Logger log = LoggerFactory.getLogger(AuthContextUtils.class);
    private static final String SUB_POINT = ".";

    public AuthContextUtils() {
    }

    public static SecurityClientBo decodeToken(String token) {
        String[] tokenValues = StrUtil.split(token, ".").toArray(new String[0]);
        byte[] base64Token = (tokenValues.length > 2 ? tokenValues[1] : tokenValues[0]).getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Base64.decode(base64Token);
        String jsonStr = new String(decoded, StandardCharsets.UTF_8);
        return (SecurityClientBo)JSON.parseObject(jsonStr, SecurityClientBo.class);
    }

    public static Boolean isExpired(SecurityClientBo securityClientBo) {
        return System.currentTimeMillis() > securityClientBo.getExp() * 1000L;
    }

    public static Boolean isExpired(String token) {
        return isExpired(decodeToken(token));
    }

    public static Boolean isAccountExpired(SecurityClientBo securityClientBo) {
        return System.currentTimeMillis() > securityClientBo.getExpiryTime();
    }

    public static Boolean isAccountInvalid(SecurityClientBo securityClientBo) {
        return securityClientBo.getInvalid();
    }

    public static SecurityClientBo getCurrentClientInfo() {
        IAccount account = CurrentAccountHolder.get();
        return ObjectUtil.isNotNull(account) && account instanceof SecurityClientBo ? (SecurityClientBo)account : null;
    }
}

