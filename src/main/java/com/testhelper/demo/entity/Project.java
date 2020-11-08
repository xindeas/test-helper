package com.testhelper.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name="tb_project")
public class Project {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name="id")
    private Long id;
    @Column(name="name",length=50)
    private String name;
    @Column(name="belongs_to")
    private Long belongsTo;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="create_by",length=50)
    private String createBy;
    @Column(name="modify_date")
    private Date modifyDate;
    @Column(name="modify_by",length=50)
    private String modifyBy;
}
