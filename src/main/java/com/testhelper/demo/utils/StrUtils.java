package com.testhelper.demo.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class StrUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
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

    /**
     * 下划线转首字母小写驼峰
     * @param ori
     * @return
     */
    public static String lineLinkToLowerCamel(String ori) {
        String result = ori;
        result = result.toLowerCase();
        Matcher matcher = linePattern.matcher(result);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转首字母大写驼峰
     * @param ori
     * @return
     */
    public static String lineLinkToUpperCamel(String ori) {
        String result = ori;
        result = result.toLowerCase();
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        Matcher matcher = linePattern.matcher(result);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    public static String firstWordToUpper(String ori) {
        String result = ori;
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }
    public static String firstWordToLower(String ori) {
        String result = ori;
        result = result.substring(0, 1).toLowerCase() + result.substring(1);
        return result;
    }
}
