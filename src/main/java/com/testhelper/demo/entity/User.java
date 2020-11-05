package com.testhelper.demo.entity;


import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name="id")
    private Long id;
    @Column(name="login",length=50)
    private String login;
    @Column(name="name",length=50)
    private String name;
    @Column(name="pwd",length=50)
    private String pwd;
    @Column(name="mobile",length=11)
    private String mobile;
    @Column(name="email",length=50)
    private String email;
    @Column(name="avatar",length=250)
    private String avatar;
    @Column(name="role",length=20)
    private String role;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="create_by",length=50)
    private String createBy;
    @Column(name="modify_date")
    private Date modifyDate;
    @Column(name="modify_by",length=50)
    private String modifyBy;
}
