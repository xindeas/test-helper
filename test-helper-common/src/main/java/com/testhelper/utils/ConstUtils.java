package com.testhelper.utils;

/**
 * 常量
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class ConstUtils {
    // 成功码
    public static final String CODE_SUCCESS = "200";
    // 失败码
    public static final String CODE_FAILD = "500";
    // 登录过期
    public static final String CODE_EXPIRED = "501";

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
        // 已关闭，发布完成
        CLOSED,
        // 重新打开，测试失败重新打开
        REOPEN,
        // 待确认，开发人员确认
        WAITE_CONFIRM
    };
}
