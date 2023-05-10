package com.example.demo;

import com.example.demo.util.RsaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("haha");
    }

    @Test
    void rsaTest() {
        String encryptedText = RsaUtils.publicEncrypt("y7W7nK2aXnKmqJv3");
        System.out.println("加密后：" + encryptedText);
        String decryptedText = RsaUtils.privateDecrypt(encryptedText);
        System.out.println("解密后：" + decryptedText);
    }

    @Test
    void generateRsaTest() {
        Map<String, String> map = RsaUtils.rsaGenerate();
        System.out.println("公钥：" + map.get("publicKey"));
        System.out.println("私钥：" + map.get("privateKey"));
    }
}
