package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 9:25
 */
@Getter
@Setter
public class DefectCommentPo {
    private Long id;
    private Long defectId;
    private Long userId;
    private String desc;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
}
