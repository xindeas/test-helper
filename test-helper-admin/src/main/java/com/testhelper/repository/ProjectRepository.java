package com.testhelper.repository;

import com.testhelper.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    Project findProjectById(Long id);
}
