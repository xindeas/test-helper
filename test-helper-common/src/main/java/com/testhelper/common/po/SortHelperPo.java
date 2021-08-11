package com.testhelper.common.po;

import com.querydsl.core.types.Order;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class SortHelperPo {
    /**
     * 列名与实体类对应
     */
    private String column;
    /**
     * 排序方式,ASC升序，DESC降序
     */
    private Order order;
}
