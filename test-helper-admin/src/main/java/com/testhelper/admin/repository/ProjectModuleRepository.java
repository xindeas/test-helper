package com.testhelper.admin.repository;


import com.testhelper.admin.entity.ProjectModule;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date:  */
@Repository
public interface ProjectModuleRepository extends org.springframework.data.jpa.repository.JpaRepository<ProjectModule, Long> {

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    ProjectModule findProjectModuleById(Long id);
}