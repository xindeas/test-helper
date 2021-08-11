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
public class ProjectPo {
    private Long id;
    private Long belongsTo;
    private String versionNo;
    private String name;
    private Boolean enabled;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
