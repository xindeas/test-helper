package com.testhelper.common.service;

import com.testhelper.common.entity.Log;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.pojo.LogPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public interface LogService {
    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<Log, LogPo> query(PageHelperPo<Log, LogPo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public Log load(Long id);

    /**
     * 编辑
     *
     * @param log
     * @return
     */
    public Log save(Log log);

    /**
     * 新增
     *
     * @param log
     * @return
     */
    public Log add(Log log);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id);
}
