package com.testhelper.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name="tb_project_auth")
public class ProjectAuth {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name="id")
    private Long id;
    @Column(name="project_id")
    private Long projectId;
    @Column(name="user_id")
    private Long userId;
}
