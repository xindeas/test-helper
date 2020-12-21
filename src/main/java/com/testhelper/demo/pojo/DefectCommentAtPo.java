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
public class DefectCommentAtPo {
    private Long id;
    private Long commentId;
    private Long userId;
    private Long referUserId;
    private Boolean readFlag;
    private Date createDate;
    private String createBy;
}
