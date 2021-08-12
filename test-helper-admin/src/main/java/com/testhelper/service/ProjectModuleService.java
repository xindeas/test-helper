package com.testhelper.service;


import com.testhelper.entity.ProjectModule;
import com.testhelper.pojo.ProjectModulePo;
import com.testhelper.po.PageHelperPo;

/**
 * @Author: Xindeas
 * @Date:
 */
public interface ProjectModuleService {

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<ProjectModule, ProjectModulePo> query(PageHelperPo<ProjectModule, ProjectModulePo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public ProjectModule load(Long id);

    /**
     * 编辑
     *
     * @param projectModule
     * @return
     */
    public ProjectModule save(ProjectModule projectModule, String userLogin);

    /**
     * 新增
     *
     * @param projectModule
     * @return
     */
    public ProjectModule add(ProjectModule projectModule, String userLogin);

    /**
     * 删除
     *
     * @param id 流水号
     */
    public void delete(Long id);
}
