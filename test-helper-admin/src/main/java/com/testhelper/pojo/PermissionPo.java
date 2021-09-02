package com.testhelper.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2021 年 09 月 02 日 星期四 08 点 49 分 50 秒
 */
@Setter
@Getter
public class PermissionPo {
    private Long id;
    private Long parentId;
    private String name;
    private String type;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
}
