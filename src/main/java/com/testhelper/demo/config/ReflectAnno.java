package com.testhelper.demo.config;

/**
 * pojo类中标注字段来源
 */
public @interface ReflectAnno {
    Class reClass();
    String column();
    String instance();
}
