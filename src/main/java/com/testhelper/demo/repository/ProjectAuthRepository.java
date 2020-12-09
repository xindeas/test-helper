package com.testhelper.demo.repository;

import com.testhelper.demo.entity.ProjectAuth;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAuthRepository extends BaseRepository<ProjectAuth, Long> {
    ProjectAuth findProjectAuthById(Long id);

}
