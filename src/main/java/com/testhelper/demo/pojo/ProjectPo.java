package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectPo {
    private Long id;
    private Long belongsTo;
    private String name;
    private Boolean enabled;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
