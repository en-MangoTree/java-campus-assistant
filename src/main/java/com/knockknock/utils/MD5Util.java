package com.knockknock.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @CreateTime: 2022-05-21 22:48
 * @Description: MD5加密工具类
 * MD5加密为单向加密（不可逆），每次加密结果不变
 * BCryptPasswordEncoder(强散哈希算法)为双向加密（可逆），每次加密结果不同
 */
@Slf4j
public class MD5Util {

    /**
     * MD5加密
     * @param password 要加密的内容
     * @return 32位的加密串
     */
    public static String MD5(String password) {
        if(null != password) {
            byte[] bytes = null;
            try {
                bytes = MessageDigest.getInstance("MD5").digest(password.getBytes());
            } catch (NoSuchAlgorithmException e) {
                log.error("没有MD5这个加密算法！");
            }
//            由MD5加密得到的字节数组转换为16进制数字
            StringBuilder code = new StringBuilder(new BigInteger(1, bytes).toString(16));
            for (int i = 0; i < 32 - code.length(); i++) {
                code.insert(0, "0");
            }
            return code.toString();
        } else {
            return null;
        }

    }
}
