package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:14
 */
@Getter
@Setter
public class DefectPo {
    private Long id;
    private Long projectId;
    private String title;
    private String defectNo;
    private String remark;
    private String targetVer;
    private Long moduleId;
    private Long assignTo;
    private Long findBy;
    private Long testBy;
    private String status;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
