package com.testhelper.demo.service;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.User;

public interface ProjectService {
    public Project load(Long id);
    public Project save(Project project);
    public Project add(Project project);
    public void delete(Long id);
}
