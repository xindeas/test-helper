package com.testhelper.repository;

import com.testhelper.entity.ProjectAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface ProjectAuthRepository extends JpaRepository<ProjectAuth, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    ProjectAuth findProjectAuthById(Long id);

    /**
     * 根据项目ID查询项目授权
     * @param projectId
     * @return
     */
    List<ProjectAuth> findProjectAuthByProjectId(Long projectId);

    /**
     * 根据项目ID删除授权
     * @param projectId
     */
    void deleteProjectAuthsByProjectId(Long projectId);

}
