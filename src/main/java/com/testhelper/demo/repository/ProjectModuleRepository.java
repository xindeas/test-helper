package com.testhelper.demo.repository;


import com.testhelper.demo.entity.ProjectModule;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date:  */
@Repository
public interface ProjectModuleRepository extends BaseRepository<ProjectModule, Long> {

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    ProjectModule findProjectModuleById(Long id);
}
