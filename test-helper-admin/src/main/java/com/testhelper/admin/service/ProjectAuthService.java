package com.testhelper.admin.service;

import com.testhelper.admin.dto.ProjectAuthDto;
import com.testhelper.admin.entity.ProjectAuth;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.ProjectAuthPo;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public interface ProjectAuthService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    public PageHelperPo<ProjectAuth, ProjectAuthPo> query(PageHelperPo<ProjectAuth, ProjectAuthPo> page);

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    public ProjectAuth load(Long id);

    /**
     * 编辑
     * @param project
     * @return
     */
    public ProjectAuth save(ProjectAuth project);

    /**
     * 新增
     * @param project
     * @return
     */
    public ProjectAuth add(ProjectAuth project);

    /**
     * 编辑或新增
     * @param projectId
     * @param list
     * @return
     */
    public List<ProjectAuth> saveOrAdd(Long projectId, List<ProjectAuth> list);

    /**
     * 根据项目ID查询授权
     * @param projectId
     * @return
     */
    public List<ProjectAuth> findAllByProjectId(Long projectId);

    /**
     * 加载编辑页面时查询授权信息
     * @param projectId
     * @return
     */
    public List<ProjectAuthDto> findAuthUsersByProjectId(Long projectId);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);
}
