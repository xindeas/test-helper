package com.testhelper.demo.service;

import com.testhelper.demo.dto.ProjectDto;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectPo;

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
     * @return
     */
    public Project save(Project project);

    /**
     * 新增
     * @param project
     * @return
     */
    public Project add(Project project);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);
}
