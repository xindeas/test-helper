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
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="name", columnDefinition="项目名称", length=50)
    private String name;
    @Column(name="belongs_to", columnDefinition="所属用户")
    private Long belongsTo;
    @Column(name="enabled", columnDefinition="启用状态")
    private Boolean enabled;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by", columnDefinition="创建人", length=50)
    private String createBy;
    @Column(name="modify_date", columnDefinition="修改时间")
    private Date modifyDate;
    @Column(name="modify_by", columnDefinition="修改人", length=50)
    private String modifyBy;
}
