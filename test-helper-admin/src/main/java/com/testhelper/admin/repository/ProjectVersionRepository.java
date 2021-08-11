package com.testhelper.admin.repository;

import com.testhelper.admin.entity.ProjectVersion;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:02
 */
@Repository
public interface ProjectVersionRepository extends org.springframework.data.jpa.repository.JpaRepository<ProjectVersion, Long> {
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
