package com.testhelper.demo.component;

import com.testhelper.demo.entity.Log;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.LogPo;
import com.testhelper.demo.service.LogService;
import com.testhelper.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
public class LogComponent {
    @Autowired
    private LogService logService;

    public ResultHelperPo query(PageHelperPo<Log, LogPo> page) {
        return new ResultHelperPo(true, logService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, logService.load(id), "");
    }
    public ResultHelperPo save(Log log) {
        if (null == log.getId()) {
            return new ResultHelperPo(false, log, "修改异常");
        }
        return new ResultHelperPo(true, logService.save(log), "");
    }
    public ResultHelperPo add(Log log) {
        if (null != log.getId()) {
            return new ResultHelperPo(false, log, "新增异常");
        }
        return new ResultHelperPo(true, logService.add(log), "");
    }
    public ResultHelperPo delete(Long id) {
        logService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
