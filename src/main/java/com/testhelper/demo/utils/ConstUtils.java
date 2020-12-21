package com.testhelper.demo.utils;

/**
 * 常量
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class ConstUtils {
    public static final String CODE_200 = "200";
    public static final String CODE_500 = "500";

    /**
     * 缺陷状态
     */
    public static enum DEFECT_STATUS {
        // 新建
        NEW,
        // 处理中
        SOLVING,
        // 已解决
        SOLVED,
        // 待发布
        WAITE_PUB,
        // 已关闭
        CLOSED,
        // 重新打开
        REOPEN,
        // 测试失败
        FAILED,
        // 待确认
        WAITE_CONFIRM
    };
}
