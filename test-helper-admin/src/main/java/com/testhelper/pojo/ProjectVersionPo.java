package com.testhelper.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 13:42
 */
@Getter
@Setter
public class ProjectVersionPo {
    private Long id;
    private Long projectId;
    private String versionNo;
    private String remark;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
