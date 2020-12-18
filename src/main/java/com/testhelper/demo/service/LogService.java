package com.testhelper.demo.service;

import com.testhelper.demo.entity.Log;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.LogPo;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public interface LogService {
    /**
     * 分页查询
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<Log, LogPo> query(PageHelperPo<Log, LogPo> page);

    /**
     * 根据ID加载实体
     * @param id 流水号
     * @return
     */
    public Log load(Long id);

    /**
     * 编辑
     * @param log
     * @return
     */
    public Log save(Log log);

    /**
     * 新增
     * @param log
     * @return
     */
    public Log add(Log log);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);
}
