package com.testhelper.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
}
