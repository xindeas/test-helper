package com.testhelper.admin.service;

import com.testhelper.admin.dto.ProjectDto;
import com.testhelper.admin.entity.Project;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.ProjectPo;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public interface ProjectService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    public PageHelperPo<ProjectDto, ProjectPo> query(PageHelperPo<ProjectDto, ProjectPo> page);

    /**
     * 查询下拉框
     * @param userId
     * @return
     */
    public List<Project> queryForOptions(Long userId);

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    public Project load(Long id);

    /**
     * 编辑
     * @param project
     * @param userLogin
     * @return
     */
    public Project save(Project project, String userLogin);

    /**
     * 新增
     * @param project
     * @return
     */
    public Project add(Project project, String userLogin);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);
}
