package com.testhelper.config;

/**
 * pojo类中标注字段来源
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public @interface ReflectAnno {
    // 关联实体类
    Class reClass();
    // 列名，关联实体类中的字段名
    String column();
    // 别名
    String instance();
}
