package com.testhelper.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @Author: Xindeas
 * @Date: 2020/12/31 17:03
 */
@Setter
@Getter
public class SchemaColumn {
    // 字段名
    private String columnName;
    // 字段实体名
    private String columnEntityName;
    // 字段实体名首字母大写
    private String upperEntityName;
    // 数据类型
    private String dataType;
    // 最大长度
    private BigInteger characterMaximumLength;
    // 是否是主键
    private String columnKey;
    // 中文说明
    private String columnComment;
    /**
     * java类型
     */
    private String javaType;
}
