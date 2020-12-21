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
}
