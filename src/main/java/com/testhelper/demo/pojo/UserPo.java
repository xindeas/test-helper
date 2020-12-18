package com.testhelper.demo.pojo;

import com.testhelper.demo.config.ReflectAnno;
import com.testhelper.demo.entity.QProject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class UserPo {
    private Long id;
    private String login;
    private String name;
    private String pwd;
    private String mobile;
    private String email;
    private String avatar;
    private String role;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;
    private List<Long> idNotIn;
}
