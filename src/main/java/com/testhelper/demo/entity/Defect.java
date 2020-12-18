package com.testhelper.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:05
 */
@Setter
@Getter
@Entity
@Table(name="tb_defect")
public class Defect {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="title", columnDefinition="标题", length=255)
    private String title;
    @Column(name="defect_no", columnDefinition="缺陷编号", length=50)
    private String defectNo;
    @Column(name="desc", columnDefinition="详细描述")
    private String desc;
    @Column(name="target_ver", columnDefinition="目标版本", length=50)
    private String targetVer;
    @Column(name="module", columnDefinition="模块", length=50)
    private String module;
    @Column(name="status", columnDefinition="状态", length=10)
    private String status;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by", columnDefinition="创建人", length=50)
    private String createBy;
    @Column(name="modify_date", columnDefinition="修改时间")
    private Date modifyDate;
    @Column(name="modify_by", columnDefinition="修改人", length=50)
    private String modifyBy;
}
