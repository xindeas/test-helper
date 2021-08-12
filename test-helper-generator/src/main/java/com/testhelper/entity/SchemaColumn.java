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
    private String columnName;
    private String dataType;
    private BigInteger characterMaximumLength;
    private String columnKey;
    private String columnComment;
    /**
     * java类型
     */
    private String javaType;
}
