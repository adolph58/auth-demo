package com.example.demo.auth;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ISecurityClientService接口实现类
 *
 * @author hp
 * @since 2020-12-22
 */
@Component("securityClientService")
public class SecurityClientBoServiceImpl implements ISecurityClientService {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public SecurityClientBo getClientByClientId(String clientId) {
        //以下内容为硬编码，实际应验证clientId并查询clientId的信息（基本信息、角色、资源权限）
        SecurityClientBo securityClientBo = new SecurityClientBo();
        Set<String> resourceIds = CollectionUtil.newHashSet(appName);
        securityClientBo.setAud(resourceIds);
        // TODO: 根据clientId获取 用户/客户端 的权限信息
        Set<String> res = CollectionUtil.newHashSet();
        res.add("user:save");
        res.add("user:page");
        res.add("auth:user-info");
        securityClientBo.setAuthorities(res);
        securityClientBo.setClientId(clientId);
        Map<String, Object> extension = new HashMap<>();
        extension.put("username", "测试用户");
        securityClientBo.setExtension(extension);
        //securityClientBo.setPassword(passwordEncoder.encode("123456"));
		securityClientBo.setInvalid(false);
        return securityClientBo;
    }
}
