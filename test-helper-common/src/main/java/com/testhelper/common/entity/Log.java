package com.testhelper.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Setter
@Getter
@Entity
@Table(name="tb_log")
public class Log {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="target_tb", columnDefinition="关联表", length=50)
    private String targetTb;
    @Column(name="target_id", columnDefinition="关联表ID")
    private Long targetId;
    @Column(name="remark", columnDefinition="操作描述",length=500)
    private String remark;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by", columnDefinition="创建人",length=50)
    private String createBy;
}
