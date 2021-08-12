package com.testhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 9:16
 */
@Setter
@Getter
@Entity
@Table(name="tb_defect_comment_at")
public class DefectCommentAt implements Serializable {
    @Id
    /**
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "uuid")
     */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="流水号")
    private Long id;
    @Column(name="comment_id", columnDefinition="评论ID")
    private Long commentId;
    @Column(name="user_id", columnDefinition="评论用户")
    private Long userId;
    @Column(name="refer_user_id", columnDefinition="被@的用户ID")
    private Long referUserId;
    @Column(name="read_flag", columnDefinition="是否已读")
    private Boolean readFlag;
    @Column(name="create_date", columnDefinition="创建时间")
    private Date createDate;
    @Column(name="create_by", columnDefinition="创建人", length=50)
    private String createBy;
}
