package com.testhelper.demo.utils;

import com.testhelper.demo.entity.Log;
import com.testhelper.demo.service.LogService;

import java.util.Date;

public class LogUtils {
    public static void log(String targetTb, Long targetId, String remark, String createBy) {
        LogService logService = SpringBeanUtils.getBean(LogService.class);
        Log log = new Log();
        log.setTargetId(targetId);
        log.setTargetTb(targetTb);
        log.setRemark(remark);
        log.setCreateBy(createBy);
        log.setCreateDate(new Date());
        logService.add(log);
    }
}
