package com.testhelper.demo.utils;

import java.util.Base64;

public class Base64Utils {
    public static String encode(String originStr) {
        byte[] bytes = originStr.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
    public static String decode(String originStr) {
        byte[] decoded = Base64.getDecoder().decode(originStr);
        return new String(decoded);
    }
}
