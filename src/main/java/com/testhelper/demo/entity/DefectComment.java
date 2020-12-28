package com.testhelper.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 9:16
 */
@Setter
@Getter
@Entity
@Table(name="tb_defect_comment")
public class DefectComment {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="defect_id", columnDefinition="缺陷ID")
    private Long defectId;
    @Column(name="user_id", columnDefinition="评论用户")
    private Long userId;
    @Column(name="remark", columnDefinition="评论内容")
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
