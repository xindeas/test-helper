package com.testhelper.component;

import com.testhelper.entity.ProjectAuth;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.ProjectAuthPo;
import com.testhelper.service.ProjectAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
public class ProjectAuthComponent {
    @Autowired
    private ProjectAuthService projectAuthService;

    public ResultHelperPo query(PageHelperPo<ProjectAuth, ProjectAuthPo> page) {
        return new ResultHelperPo(true, projectAuthService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, projectAuthService.load(id), "");
    }
    public ResultHelperPo save(ProjectAuth projectAuth) {
        if (null == projectAuth.getId()) {
            return new ResultHelperPo(false, projectAuth, "修改异常");
        }
        return new ResultHelperPo(true, projectAuthService.save(projectAuth), "");
    }
    public ResultHelperPo add(ProjectAuth projectAuth) {
        if (null != projectAuth.getId()) {
            return new ResultHelperPo(false, projectAuth, "新增异常");
        }
        return new ResultHelperPo(true, projectAuthService.add(projectAuth), "");
    }
    public ResultHelperPo delete(Long id) {
        projectAuthService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
