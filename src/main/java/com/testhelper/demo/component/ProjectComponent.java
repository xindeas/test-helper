package com.testhelper.demo.component;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.ProjectPo;
import com.testhelper.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectComponent {
    @Autowired
    private ProjectService projectService;

    public ResultHelperPo query(PageHelperPo<Project, ProjectPo> page) {
        return new ResultHelperPo(true, projectService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, projectService.load(id), "");
    }
    public ResultHelperPo save(Project project) {
        if (null == project.getId()) {
            return new ResultHelperPo(false, project, "修改异常");
        }
        return new ResultHelperPo(true, projectService.save(project), "");
    }
    public ResultHelperPo add(Project project) {
        if (null != project.getId()) {
            return new ResultHelperPo(false, project, "新增异常");
        }
        return new ResultHelperPo(true, projectService.add(project), "");
    }
    public ResultHelperPo delete(Long id) {
        projectService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
