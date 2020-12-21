package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    private String desc;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
