package com.testhelper.demo.repository;

import com.testhelper.demo.entity.Project;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    Project findProjectById(Long id);
}
