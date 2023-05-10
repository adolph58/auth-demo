package com.example.demo.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arte
 * @create 2023/4/10.
 * hutool RSA 加解密
 */
@Component
public class RsaUtils {

    private static String publicKey;

    private static String privateKey;

    @Value("${rsa.publicKey}")
    public void setPublicKey(String publicKey) {
        RsaUtils.publicKey = publicKey;
    }

    @Value("${rsa.privateKey}")
    public void setPrivateKey(String privateKey) {
        RsaUtils.privateKey = privateKey;
    }

    /**
     * 生成 RSA
     * @param privateKey
     * @param publicKey
     * @return
     */
    public static RSA getRsa(String privateKey, String publicKey) {
        RSA rsa;
        if (StringUtils.isEmpty(privateKey) && StringUtils.isEmpty(publicKey)) {
            rsa = new RSA();
        } else {
            rsa = new RSA(privateKey, publicKey);
        }
        return rsa;
    }

    /**
     * 生成公钥私钥
     */
    public static Map<String, String> rsaGenerate() {
        Map<String, String> result = new HashMap<>();
        RSA rsa = getRsa(null, null);
        // 获得私钥
        // rsa.getPrivateKey();
        String privateKeyGenerate = rsa.getPrivateKeyBase64();
        // 获得公钥
        // rsa.getPublicKey();
        String publicKeyGenerate = rsa.getPublicKeyBase64();
        result.put("privateKey", privateKeyGenerate);
        result.put("publicKey", publicKeyGenerate);
        return result;
    }

    /**
     * 公钥加密
     */
    public static String publicEncrypt(String text) {
        RSA rsa = getRsa(privateKey, publicKey);
        String encryptedText = rsa.encryptBase64(text, KeyType.PublicKey);
        return encryptedText;
    }

    /**
     * 私钥解密
     */
    public static String privateDecrypt(String encryptedText) {
        RSA rsa = getRsa(privateKey, publicKey);
        String decryptedText = rsa.decryptStr(encryptedText, KeyType.PrivateKey);
        return decryptedText;
    }


    public static void main(String[] args) throws Exception {
//        customRsaTest();
//        existRsaTest("helloworld");
        rsaTest("hello,world");
//        defaultRsaTest(getRsa(null, null), "hello,arte");
    }

    public static void rsaTest(String text) {
        //String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1+jI4rEzVtEsw6Y5qtDPrVVN7JZ/pZMmBKZtYwE/4oslDxF1lxoPK3/2qh8ZjUVALmLHizzXp5qbN3cOa/OdcfLtTA3Vs9Nu0rGo3w9zinK4E5hFvE7ANLk+9Kxx9EH+wUA02iY6U3ns6xlAIi194vyJpi/Zkoy6Bm/SldWHAowIDAQAB";
        //String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALX6MjisTNW0SzDpjmq0M+tVU3sln+lkyYEpm1jAT/iiyUPEXWXGg8rf/aqHxmNRUAuYseLPNenmps3dw5r851x8u1MDdWz027SsajfD3OKcrgTmEW8TsA0uT70rHH0Qf7BQDTaJjpTeezrGUAiLX3i/ImmL9mSjLoGb9KV1YcCjAgMBAAECgYAYWKDfdC3TaYulv5mOLQ3FLmU81IvQbeuHnElsVk5ctWq35k2YkDaRreYCKqggIYDUDFnAPni4EmO+pyzET5GkVEI4Usk0b5z4ZW7x+IWEoYki8XwHS9UA6mZAhUMW+jUZTKmGn3JebJHco7k296KDoangtzk8XDNzgmMUB2i2AQJBAN9R/boZCakrb1taVwJcFBo7DPFpjrtWiKj9j/80P4P8A7o0Rco/MHWxDPSfrrOu81PLh2d5fGr3tAsW1AgSOAUCQQDQm2sYu3ntv+uNk928JVPo0iMXQVA7foSJyIO50P4YTsiQKLpLqSXXfypizXguxTbQyMyfDwV9007MAKrQcT6HAkB3S3RPskpP3U0KLtMmBowSixLZ7zp45rvwEiuW95EqbziKgpv5cxHu/Npf3uFTSRTg4Wt+og4Nki7ScapoeVsFAkAkTKcaKqVQ5bCLdkuDTcuIG+jXRxo7AAz4HNgvIVrVx//Gdg/U0p4vHYW2o6JiIRckxYw1sVt3RanUfdk7ZDlJAkEAoCUuVudTrH3brIay3VdWApQPYWMqcL7gyuUt+y9tnxn3JI5wv3pFethwsAd24p/TVSsw6sE3m1CFwilOyq0Rfg==";
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
        RSA rsa = getRsa(privateKey, publicKey);
        System.out.println("-----加密-----");
        System.out.println("原文：" + text);
        String encryptedText = publicEncrypt(text);
        System.out.println("加密后：" + encryptedText);

        System.out.println("-----解密-----");
        String decryptedText = privateDecrypt(encryptedText);
        System.out.println("解密后：" + decryptedText);
    }

    /**
     * byte[] 加解密
     * @param rsa
     * @param text
     */
    public static void defaultRsaTest(RSA rsa, String text) {

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(text, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        System.out.println(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes(text, CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        System.out.println(StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
    }

    /**
     * @desc: 将字符串转换成RSAPublicKey类型
     * @date 2020-6-12 11:03:05
     * @param
     * @return
     */
    public static RSAPublicKey getRSAPublidKeyBybase64(String base64s) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((new BASE64Decoder()).decodeBuffer(base64s));
        RSAPublicKey publicKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException var4) {

        }
        return publicKey;
    }

    /**
     * @desc: 将字符串转换成RSAPrivateKey类型
     * @date 2020-6-12 11:03:01
     * @param
     * @return
     */
    public static RSAPrivateKey getRSAPrivateKeyBybase64(String base64s) throws Exception{
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec((new BASE64Decoder()).decodeBuffer(base64s));
        RSAPrivateKey privateKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException var4) {
        }
        return privateKey;
    }

    /**
     * 自定义 RSA 公钥私钥
     * 只能生成公钥私钥，同样的公钥盐和私钥盐，不同批次生成的公钥密钥不同，加密后的密文只能由同一对公钥私钥来解密
     * @throws Exception
     */
    public static void customRsaTest() throws Exception {

        AsymmetricCrypto asymmetricCrypto = new AsymmetricCrypto("RSA", getRSAPrivateKeyBybase64("私钥解密"),
                getRSAPublidKeyBybase64("公钥加密"));

        String publicKey = asymmetricCrypto.getPublicKeyBase64();
        System.out.println("publicKey: "+ publicKey);
        String privateKey = asymmetricCrypto.getPrivateKeyBase64();
        System.out.println("privateKey: " + privateKey);

        String s1 = asymmetricCrypto.encryptBase64("你好呀", KeyType.PublicKey);
        System.out.println(s1);

        String s = asymmetricCrypto.decryptStr(s1, KeyType.PrivateKey);
        System.out.println(s);

        // 自定义加解密，ciphertext1 和 ciphertext2 是用不同批次，同一对自定义公钥私钥盐生成的公钥私钥加密后的密文
        String ciphertext1 = "HwOPisOQWTYKGKfZ4OL/iMLqaXDA7aLu/x4KMmfabWYqPo3cKwwZwJA0xAgDDOIetNjEsfylwCn2ovCuiscB/uPScLLcqWOCGEtazkGczKjm5tzYCYRUUJSU8m6lnPiPkFTKnjdGUvHCfq8OoIFzejEtgozm4WeCqUxWGz9OnMI=";
        String ciphertext2 = "n9kj7rCVueW0UAsw7BN5pOb+pLH/4foYfySTfqM6E9n3ev1ps/RMPBziTgaRcq6MsugG6DEtH5dFnBbSXCoF8mByq4p1jzR5Rx6ZucxbS6xGNQxv+w2AFtEv8OdGbJkRiFMS6X4ZjY+q6XIIOuVdgmOkZBbbEq0aYWqwld2/7ns=";
        String s2 = asymmetricCrypto.decryptStr(ciphertext1, KeyType.PrivateKey);
        System.out.println(s2);
        String s3 = asymmetricCrypto.decryptStr(ciphertext2, KeyType.PrivateKey);
        System.out.println(s3);
    }

    /**
     * 已知公钥私钥，测试加解密
     * @param text
     */
    public static void existRsaTest(String text) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1+jI4rEzVtEsw6Y5qtDPrVVN7JZ/pZMmBKZtYwE/4oslDxF1lxoPK3/2qh8ZjUVALmLHizzXp5qbN3cOa/OdcfLtTA3Vs9Nu0rGo3w9zinK4E5hFvE7ANLk+9Kxx9EH+wUA02iY6U3ns6xlAIi194vyJpi/Zkoy6Bm/SldWHAowIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALX6MjisTNW0SzDpjmq0M+tVU3sln+lkyYEpm1jAT/iiyUPEXWXGg8rf/aqHxmNRUAuYseLPNenmps3dw5r851x8u1MDdWz027SsajfD3OKcrgTmEW8TsA0uT70rHH0Qf7BQDTaJjpTeezrGUAiLX3i/ImmL9mSjLoGb9KV1YcCjAgMBAAECgYAYWKDfdC3TaYulv5mOLQ3FLmU81IvQbeuHnElsVk5ctWq35k2YkDaRreYCKqggIYDUDFnAPni4EmO+pyzET5GkVEI4Usk0b5z4ZW7x+IWEoYki8XwHS9UA6mZAhUMW+jUZTKmGn3JebJHco7k296KDoangtzk8XDNzgmMUB2i2AQJBAN9R/boZCakrb1taVwJcFBo7DPFpjrtWiKj9j/80P4P8A7o0Rco/MHWxDPSfrrOu81PLh2d5fGr3tAsW1AgSOAUCQQDQm2sYu3ntv+uNk928JVPo0iMXQVA7foSJyIO50P4YTsiQKLpLqSXXfypizXguxTbQyMyfDwV9007MAKrQcT6HAkB3S3RPskpP3U0KLtMmBowSixLZ7zp45rvwEiuW95EqbziKgpv5cxHu/Npf3uFTSRTg4Wt+og4Nki7ScapoeVsFAkAkTKcaKqVQ5bCLdkuDTcuIG+jXRxo7AAz4HNgvIVrVx//Gdg/U0p4vHYW2o6JiIRckxYw1sVt3RanUfdk7ZDlJAkEAoCUuVudTrH3brIay3VdWApQPYWMqcL7gyuUt+y9tnxn3JI5wv3pFethwsAd24p/TVSsw6sE3m1CFwilOyq0Rfg==";

        RSA rsa = getRsa(privateKey, publicKey);

        //获得私钥
        rsa.getPrivateKey();
        String privateKey1 = rsa.getPrivateKeyBase64();
        System.out.println(privateKey1);
        if (privateKey.equals(privateKey1)) {
            System.out.println("私钥入参和输出一样");
        }
        //获得公钥
        rsa.getPublicKey();
        String publicKey1 = rsa.getPublicKeyBase64();
        System.out.println(publicKey1);
        if (publicKey.equals(publicKey1)) {
            System.out.println("公钥入参和输出一样");
        }

        defaultRsaTest(rsa, text);
    }

}
