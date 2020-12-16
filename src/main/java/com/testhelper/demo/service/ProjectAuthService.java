package com.testhelper.demo.service;

import com.testhelper.demo.entity.ProjectAuth;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectAuthPo;

import java.util.List;

public interface ProjectAuthService {
    public PageHelperPo<ProjectAuth, ProjectAuthPo> query(PageHelperPo<ProjectAuth, ProjectAuthPo> page);
    public ProjectAuth load(Long id);
    public ProjectAuth save(ProjectAuth project);
    public ProjectAuth add(ProjectAuth project);
    public List<ProjectAuth> saveOrAdd(List<ProjectAuth> list);
    public void delete(Long id);
}
