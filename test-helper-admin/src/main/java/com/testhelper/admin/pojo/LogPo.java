package com.testhelper.admin.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class LogPo {
    private Long id;
    private String targetTb;
    private Long targetId;
    private String remark;
    private Date createDate;
    private String createBy;
}
