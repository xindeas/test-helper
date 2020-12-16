package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
