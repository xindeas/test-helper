package com.testhelper.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Setter
@Getter
@Entity
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="login", columnDefinition="账号",length=50)
    private String login;
    @Column(name="name", columnDefinition="姓名",length=50)
    private String name;
    @Column(name="pwd", columnDefinition="密码",length=50)
    private String pwd;
    @Column(name="mobile", columnDefinition="手机",length=11)
    private String mobile;
    @Column(name="email", columnDefinition="邮箱",length=50)
    private String email;
    @Column(name="avatar", columnDefinition="头像",length=250)
    private String avatar;
    @Column(name="role", columnDefinition="角色",length=20)
    private String role;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by",length=50, columnDefinition="创建人")
    private String createBy;
    @Column(name="modify_date", columnDefinition="修改时间")
    private Date modifyDate;
    @Column(name="modify_by",length=50, columnDefinition="修改人")
    private String modifyBy;
}
