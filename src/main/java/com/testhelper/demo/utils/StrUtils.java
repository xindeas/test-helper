package com.testhelper.demo.utils;


import org.apache.commons.lang3.StringUtils;

public class StrUtils {
    public static String nullToEmpty(String originStr) {
        if (StringUtils.isBlank(originStr)) {
            return "";
        }
        return originStr;
    }
}
