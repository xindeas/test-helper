package com.testhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2021 年 09 月 02 日 星期四 08 点 49 分 50 秒
 */
@Setter
@Getter
@Entity
@Table(name = "tb_permission")
public class Permission {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "流水号")
    private Long id;
    @Column(name = "parent_id", columnDefinition = "父级权限ID")
    private Long parentId;
    @Column(name = "name", columnDefinition = "权限名", length = 50)
    private String name;
    @Column(name = "type", columnDefinition = "权限类别（M：菜单；B：按钮；P：页面）", length = 1)
    private String type;
    @Column(name = "create_by", columnDefinition = "创建人", length = 50)
    private String createBy;
    @Column(name = "create_date", columnDefinition = "创建时间")
    private Date createDate;
    @Column(name = "modify_by", columnDefinition = "修改人", length = 50)
    private String modifyBy;
    @Column(name = "modify_date", columnDefinition = "修改时间")
    private Date modifyDate;
}
