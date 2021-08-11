package com.testhelper.admin.component;

import com.testhelper.admin.entity.ProjectModule;
import com.testhelper.admin.entity.User;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.po.ResultHelperPo;
import com.testhelper.admin.pojo.ProjectModulePo;
import com.testhelper.admin.service.ProjectModuleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date:
 */
@Component
public class ProjectModuleComponent {
    @Autowired
    private ProjectModuleService projectModuleService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    public ResultHelperPo query(PageHelperPo<ProjectModule, ProjectModulePo> page) {
        return new ResultHelperPo(true, projectModuleService.query(page), "");
    }

    /**
     * 加载
     *
     * @param id
     * @return
     */
    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, projectModuleService.load(id), "");
    }

    /**
     * 修改
     *
     * @param projectModule
     * @return
     */
    public ResultHelperPo save(ProjectModule projectModule) {
        if (null == projectModule.getId()) {
            return new ResultHelperPo(false, projectModule, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectModuleService.save(projectModule, user.getLogin()), "");
    }

    /**
     * 新增
     *
     * @param projectModule
     * @return
     */
    public ResultHelperPo add(ProjectModule projectModule) {
        if (null != projectModule.getId()) {
            return new ResultHelperPo(false, projectModule, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, projectModuleService.add(projectModule, user.getLogin()), "");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public ResultHelperPo delete(Long id) {
        projectModuleService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
