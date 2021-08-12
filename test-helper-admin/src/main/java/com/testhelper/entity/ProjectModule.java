package com.testhelper.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date:  */
@Setter
@Getter
@Entity
@Table(name="tb_project_module")
public class ProjectModule implements Serializable {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="project_id", columnDefinition="项目ID")
    private Long projectId;
    @Column(name="module_name", columnDefinition="模块名", length=50)
    private String moduleName;
    @Column(name="create_by", columnDefinition="创建人", length=50)
    private String createBy;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="modify_by", columnDefinition="修改人", length=50)
    private String modifyBy;
    @Column(name="modify_date", columnDefinition="修改时间")
    private Date modifyDate;
}
