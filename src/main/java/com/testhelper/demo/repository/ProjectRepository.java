package com.testhelper.demo.repository;

import com.testhelper.demo.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {
    @Query("select p from Project p where p.id=?1")
    Project findProjectById(Long id);
}
