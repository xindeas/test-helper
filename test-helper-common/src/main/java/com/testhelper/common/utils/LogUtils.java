package com.testhelper.common.utils;

import com.testhelper.common.entity.Log;
import com.testhelper.common.service.LogService;

import java.util.Date;

/**
 * 操作记录工具
 *
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class LogUtils {
    /**
     * 记录操作记录
     *
     * @param targetTb 关联表名
     * @param targetId 关联表ID
     * @param remark   备注
     * @param createBy 创建人
     */
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
