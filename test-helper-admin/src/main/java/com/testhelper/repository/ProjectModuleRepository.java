package com.testhelper.repository;


import com.testhelper.entity.ProjectModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date:  */
@Repository
public interface ProjectModuleRepository extends JpaRepository<ProjectModule, Long> {

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    ProjectModule findProjectModuleById(Long id);
}
