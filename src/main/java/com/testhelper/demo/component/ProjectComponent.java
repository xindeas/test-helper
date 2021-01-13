package com.testhelper.demo.component;

import com.testhelper.demo.dto.ProjectDto;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.ProjectPo;
import com.testhelper.demo.service.ProjectAuthService;
import com.testhelper.demo.service.ProjectService;
import com.testhelper.demo.service.ProjectVersionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
public class ProjectComponent {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectAuthService projectAuthService;
    @Autowired
    private ProjectVersionService projectVersionService;

    public ResultHelperPo query(PageHelperPo<ProjectDto, ProjectPo> page) {
        return new ResultHelperPo(true, projectService.query(page), "");
    }

    public ResultHelperPo queryForOptions(Long userId) {
        return new ResultHelperPo(true, projectService.queryForOptions(userId), "");
    }

    public ResultHelperPo load(Long id) {
        ProjectDto dto = new ProjectDto();
        dto.setProject(projectService.load(id));
        dto.setAuthUsers(projectAuthService.findAuthUsersByProjectId(id));
        return new ResultHelperPo(true, dto, "");
    }

    public ResultHelperPo save(ProjectDto projectDto) {
        if (null == projectDto.getProject() || null == projectDto.getProject().getId()) {
            return new ResultHelperPo(false, projectDto, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Project project = projectService.save(projectDto.getProject(), user.getLogin());
        projectAuthService.saveOrAdd(project.getId(), projectDto.getAuths());
        return new ResultHelperPo(true, projectDto, "");
    }

    public ResultHelperPo add(ProjectDto projectDto) {
        if (null != projectDto.getProject() && null != projectDto.getProject().getId()) {
            return new ResultHelperPo(false, projectDto, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Project project = projectService.add(projectDto.getProject(), user.getLogin());
        projectAuthService.saveOrAdd(project.getId(), projectDto.getAuths());
        if (!CollectionUtils.isEmpty(projectDto.getProjectVersions())) {
            projectDto.getProjectVersions().forEach(item -> {
                item.setProjectId(project.getId());
                projectVersionService.add(item, user.getLogin());
            });
        }
        return new ResultHelperPo(true, projectDto, "");
    }

    public ResultHelperPo delete(Long id) {
        projectService.delete(id);
        return new ResultHelperPo(true, id, "");
    }

    public ResultHelperPo enable(Long id) {
        Project old = projectService.load(id);
        if (null == old) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (old.getEnabled()) {
            return new ResultHelperPo(false, null, old.getName() + "项目已启用无需再次启用");
        }
        old.setEnabled(true);

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectService.save(old, user.getLogin()), "");
    }

    public ResultHelperPo disable(Long id) {
        Project old = projectService.load(id);
        if (null == old) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (!old.getEnabled()) {
            return new ResultHelperPo(false, null, old.getName() + "项目已禁用无需再次禁用");
        }
        old.setEnabled(false);

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectService.save(old, user.getLogin()), "");
    }

    public ResultHelperPo switchVersion(Long projectId, String versionNo) {
        Project project = projectService.load(projectId);
        if (null != project) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            project.setVersionNo(versionNo);
            projectService.save(project, user.getLogin());
        }
        return new ResultHelperPo(true, projectId, "");
    }
}
