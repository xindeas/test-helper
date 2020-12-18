package com.testhelper.demo.component;

import com.testhelper.demo.dto.ProjectDto;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.ProjectPo;
import com.testhelper.demo.service.ProjectAuthService;
import com.testhelper.demo.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        Project project = projectService.save(projectDto.getProject());
        projectAuthService.saveOrAdd(project.getId(), projectDto.getAuths());
        return new ResultHelperPo(true, projectDto, "");
    }
    public ResultHelperPo add(ProjectDto projectDto) {
        if (null != projectDto.getProject() && null != projectDto.getProject().getId()) {
            return new ResultHelperPo(false, projectDto, "新增异常");
        }
        Project project = projectService.add(projectDto.getProject());
        projectAuthService.saveOrAdd(project.getId(), projectDto.getAuths());
        return new ResultHelperPo(true, projectDto, "");
    }
    public ResultHelperPo delete(Long id) {
        projectService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
    public ResultHelperPo enable(Long id) {
        // TODO 此处不进行复制的话在后面进行load的时候在select语句之前将会先进行一次update，具体原因未知
        Project current = new Project();
        Project old = projectService.load(id);
        BeanUtils.copyProperties(old, current);
        if (null == old) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (old.getEnabled()) {
            return new ResultHelperPo(false, null, old.getName() + "项目已启用无需再次启用");
        }
        current.setEnabled(true);

        return new ResultHelperPo(true, projectService.save(current), "");
    }
    public ResultHelperPo disable(Long id) {
        // TODO 此处不进行复制的话在后面进行load的时候在select语句之前将会先进行一次update，具体原因未知
        Project current = new Project();
        Project old = projectService.load(id);
        BeanUtils.copyProperties(old, current);
        if (null == old) {
            return new ResultHelperPo(false, null, "项目不存在");
        }
        if (!old.getEnabled()) {
            return new ResultHelperPo(false, null, old.getName() + "项目已禁用无需再次禁用");
        }
        current.setEnabled(false);

        return new ResultHelperPo(true, projectService.save(current), "");
    }
}
