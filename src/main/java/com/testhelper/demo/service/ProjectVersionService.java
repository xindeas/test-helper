package com.testhelper.demo.service;

import com.testhelper.demo.entity.ProjectVersion;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectVersionPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 13:41
 */
public interface ProjectVersionService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    public PageHelperPo<ProjectVersion, ProjectVersionPo> query(PageHelperPo<ProjectVersion, ProjectVersionPo> page);

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    public ProjectVersion load(Long id);

    /**
     * 根据项目ID和版本号查版本
     * @param projectId
     * @param versionNo
     * @return
     */
    ProjectVersion findProjectVersionByProjectIdAndVersionNo(Long projectId, String versionNo);

    /**
     * 编辑
     * @param projectVersion
     * @return
     */
    public ProjectVersion save(ProjectVersion projectVersion);

    /**
     * 新增
     * @param projectVersion
     * @return
     */
    public ProjectVersion add(ProjectVersion projectVersion);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);
}
