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
public class DefectCommentPo implements Serializable {
    private Long id;
    private String createBy;
    private Date createDate;
    private Long defectId;
    private String modifyBy;
    private Date modifyDate;
    private Long reactCommentId;
    private String remark;
    private Long userId;
}
