package com.testhelper.admin.service;

import com.testhelper.admin.dto.DefectCommentDto;
import com.testhelper.admin.entity.DefectComment;
import com.testhelper.admin.pojo.DefectCommentPo;
import com.testhelper.common.po.PageHelperPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:04
 */
public interface DefectCommentService {
    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<DefectComment, DefectCommentPo> query(PageHelperPo<DefectComment, DefectCommentPo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public DefectComment load(Long id);

    /**
     * 编辑
     *
     * @param defectComment
     * @return
     */
    public DefectComment save(DefectComment defectComment, String userLogin);

    /**
     * 新增
     *
     * @param defectComment
     * @return
     */
    public DefectCommentDto add(DefectCommentDto defectComment, String userLogin);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id);
}
