package com.testhelper.demo.repository;

import com.testhelper.demo.entity.ProjectVersion;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:02
 */
public interface ProjectVersionRepository extends BaseRepository<ProjectVersion, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    ProjectVersion findProjectVersionById(Long id);

    /**
     * 根据项目ID和版本号查版本
     * @param projectId
     * @param versionNo
     * @return
     */
    ProjectVersion findProjectVersionByProjectIdAndVersionNo(Long projectId, String versionNo);
}
