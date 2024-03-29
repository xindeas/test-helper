package com.testhelper.service;

import com.testhelper.entity.DefectCommentAt;
import com.testhelper.pojo.DefectCommentAtPo;
import com.testhelper.po.PageHelperPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:04
 */
public interface DefectCommentAtService {
    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<DefectCommentAt, DefectCommentAtPo> query(PageHelperPo<DefectCommentAt, DefectCommentAtPo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public DefectCommentAt load(Long id);

    /**
     * 编辑
     *
     * @param defectCommentAt
     * @return
     */
    public DefectCommentAt save(DefectCommentAt defectCommentAt, String userLogin);

    /**
     * 新增
     *
     * @param defectCommentAt
     * @return
     */
    public DefectCommentAt add(DefectCommentAt defectCommentAt, String userLogin);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id);
}
