package com.testhelper.admin.repository;

import com.testhelper.admin.entity.Project;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface ProjectRepository extends org.springframework.data.jpa.repository.JpaRepository<Project, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    Project findProjectById(Long id);
}