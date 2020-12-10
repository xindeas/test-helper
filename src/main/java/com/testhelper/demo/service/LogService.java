package com.testhelper.demo.service;

import com.testhelper.demo.entity.Log;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.LogPo;

import java.util.List;

public interface LogService {
    public PageHelperPo<Log, LogPo> query(PageHelperPo<Log, LogPo> page);
    public Log load(Long id);
    public Log save(Log log);
    public Log add(Log log);
    public void delete(Long id);
}
