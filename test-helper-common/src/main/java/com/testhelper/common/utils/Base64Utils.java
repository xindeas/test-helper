package com.testhelper.common.utils;

import java.util.Base64;

/**
 * base64工具
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class Base64Utils {
    /**
     * 加密
     * @param originStr 原字符串
     * @return
     */
    public static String encode(String originStr) {
        byte[] bytes = originStr.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解密
     * @param originStr 原字符串
     * @return
     */
    public static String decode(String originStr) {
        byte[] decoded = Base64.getDecoder().decode(originStr);
        return new String(decoded);
    }
}
