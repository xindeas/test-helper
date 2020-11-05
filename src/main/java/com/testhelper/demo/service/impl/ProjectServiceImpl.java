package com.testhelper.demo.service.impl;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.repository.ProjectRepository;
import com.testhelper.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project load(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project add(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
