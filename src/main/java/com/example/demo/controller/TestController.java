package com.example.demo.controller;

import com.example.demo.auth.AuthContextUtils;
import com.example.demo.auth.SecurityClientBo;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/test")
@RestController
@Validated
@Api(value = "测试", tags = "测试")
public class TestController {

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/redisTest")
    public Result redisTest(String key, String value) {
        redisUtils.setex(key, value, 10, TimeUnit.MINUTES);
        Object v = redisUtils.get(key);
        return Result.success(v);
    }

    @GetMapping(value = "/hello", name = "你好")
    public String helloWorld() {
        return "hello world";
    }

    /**
     * 修改为没有 GLF4 版本后，获取不到用户信息
     * @return
     */
    @GetMapping(value = "/loginUser/info", name = "获取当前用户信息")
    public SecurityClientBo getMyInfo() {
        SecurityClientBo currentClientInfo = AuthContextUtils.getCurrentClientInfo();
        return currentClientInfo;
    }

    @PostMapping(value = "/delete-user/{userId}", name = "删除用户")
    public String testUserSave(Long userId) {
        return "用户" + userId + "已删除";
    }
}
