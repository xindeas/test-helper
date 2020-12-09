package com.testhelper.demo.service;

import com.testhelper.demo.entity.ProjectAuth;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectAuthPo;

public interface ProjectAuthService {
    public PageHelperPo<ProjectAuth, ProjectAuthPo> query(PageHelperPo<ProjectAuth, ProjectAuthPo> page);
    public ProjectAuth load(Long id);
    public ProjectAuth save(ProjectAuth project);
    public ProjectAuth add(ProjectAuth project);
    public void delete(Long id);
}
