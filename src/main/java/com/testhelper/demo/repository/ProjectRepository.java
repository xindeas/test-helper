package com.testhelper.demo.repository;

import com.testhelper.demo.entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {
    Project findProjectById(Long id);

}
