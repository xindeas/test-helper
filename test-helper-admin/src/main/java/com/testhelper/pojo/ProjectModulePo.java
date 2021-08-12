package com.testhelper.pojo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date:  */
@Setter
@Getter
public class ProjectModulePo implements Serializable {
    private Long id;
    private Long projectId;
    private String moduleName;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
}
