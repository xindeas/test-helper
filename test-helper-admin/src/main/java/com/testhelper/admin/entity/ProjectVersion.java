package com.testhelper.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 13:39
 */
@Setter
@Getter
@Entity
@Table(name="tb_project_version")
public class ProjectVersion {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="project_id", columnDefinition="项目ID")
    private Long projectId;
    @Column(name="version_no", columnDefinition="版本号")
    private String versionNo;
    @Column(name="remark", columnDefinition="版本说明")
    private String remark;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by", columnDefinition="创建人", length=50)
    private String createBy;
    @Column(name="modify_date", columnDefinition="修改时间")
    private Date modifyDate;
    @Column(name="modify_by", columnDefinition="修改人", length=50)
    private String modifyBy;
}
