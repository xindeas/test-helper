package com.testhelper.component;

import com.testhelper.entity.ProjectVersion;
import com.testhelper.entity.User;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.ProjectVersionPo;
import com.testhelper.service.ProjectVersionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:01
 */
@Component
public class ProjectVersionComponent {
    @Autowired
    private ProjectVersionService projectVersionService;

    public ResultHelperPo query(PageHelperPo<ProjectVersion, ProjectVersionPo> page) {
        return new ResultHelperPo(true, projectVersionService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, projectVersionService.load(id), "");
    }
    public ResultHelperPo checkUnique(Long projectId, Long id, String no) {
        ProjectVersion pv = projectVersionService.findProjectVersionByProjectIdAndVersionNo(projectId, no);
        if (null == pv || pv.getId().equals(id)) {
            return new ResultHelperPo(true, null, no + "不存在");
        }
        return new ResultHelperPo(false, pv, no + "已存在");
    }
    public ResultHelperPo save(ProjectVersion projectVersion) {
        if (null == projectVersion.getId()) {
            return new ResultHelperPo(false, projectVersion, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectVersionService.save(projectVersion, user.getLogin()), "");
    }
    public ResultHelperPo add(ProjectVersion projectVersion) {
        if (null != projectVersion.getId()) {
            return new ResultHelperPo(false, projectVersion, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectVersionService.add(projectVersion, user.getLogin()), "");
    }
    public ResultHelperPo delete(Long id) {
        projectVersionService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
