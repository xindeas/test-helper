package com.testhelper.dto;

import com.testhelper.entity.DefectComment;
import com.testhelper.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2021/1/12 13:35
 */
@Getter
@Setter
public class DefectCommentDto {
    private DefectComment defectComment;
    private DefectComment referDefectComment;
    // 评论用户
    private User user;
    private List<Long> referUserIds;
}
