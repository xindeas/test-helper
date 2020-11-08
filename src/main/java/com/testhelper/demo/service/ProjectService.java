package com.testhelper.demo.service;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectPo;

public interface ProjectService {
    public PageHelperPo<Project, ProjectPo> query(PageHelperPo<Project, ProjectPo> page);
    public Project load(Long id);
    public Project save(Project project);
    public Project add(Project project);
    public void delete(Long id);
}
