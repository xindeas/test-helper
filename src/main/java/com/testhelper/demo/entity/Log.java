package com.testhelper.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name="tb_log")
public class Log {
    @Id
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="target_tb",length=50)
    private String targetTb;
    @Column(name="target_id")
    private Long targetId;
    @Column(name="remark",length=255)
    private String remark;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="create_by",length=50)
    private String createBy;
}
