package com.testhelper.admin.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    private List<String> targetVer;
    private List<Long> moduleId;
    private List<Long> assignTo;
    private List<Long> findBy;
    private List<Long> testBy;
    private List<String> status;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
