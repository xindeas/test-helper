package com.testhelper.demo.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class StrUtils {
    /**
     * null转为空字符串
     * @param originStr 原字符串
     * @return
     */
    public static String nullToEmpty(String originStr) {
        if (StringUtils.isBlank(originStr)) {
            return "";
        }
        return originStr;
    }
}
