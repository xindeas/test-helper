package com.testhelper.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Setter
@Getter
@Entity
@Table(name="tb_project_auth")
public class ProjectAuth {
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
    @Column(name="user_id", columnDefinition="用户ID")
    private Long userId;
}
