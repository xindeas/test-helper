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

    public ResultHelperPo queryForOptions(Long userId) {
        return new ResultHelperPo(true, projectService.queryForOptions(userId), "");
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
    public ResultHelperPo enable(Long id) {
        Project project = projectService.load(id);
        if (null == project) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (project.getEnabled()) {
            return new ResultHelperPo(false, null, project.getName() + "项目已启用无需再次启用");
        }
        project.setEnabled(true);

        return new ResultHelperPo(true, projectService.save(project), "");
    }
    public ResultHelperPo disable(Long id) {
        Project project = projectService.load(id);
        if (null == project) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (!project.getEnabled()) {
            return new ResultHelperPo(false, null, project.getName() + "项目已禁用无需再次禁用");
        }
        project.setEnabled(false);

        return new ResultHelperPo(true, projectService.save(project), "");
    }
}
