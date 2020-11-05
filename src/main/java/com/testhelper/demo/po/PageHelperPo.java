package com.testhelper.demo.po;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页辅助类
 * @param <T> 返回值类型，一般建议为dto类
 * @param <P> 过滤条件类型，一般建议为pojo
 */
@Getter
@Setter
public class PageHelperPo<T, P> {
    /**
     * 页码
     */
    private Integer pageIndex;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer totalCount;
    /**
     * 排序方式
     */
    private List<SortHelperPo> sorts;
    /**
     * 是否分页
     */
    private Boolean pagination = true;
    /**
     * 结果集
     */
    private List<T> result;
    /**
     * 过滤条件
     */
    private P filter;
}
