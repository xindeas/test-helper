package com.testhelper.demo.component;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.testhelper.demo.entity.ProjectModule;
import com.testhelper.demo.pojo.ProjectModulePo;
import com.testhelper.demo.service.ProjectModuleService;
/**
 * @Author: Xindeas
 * @Date:  */
@Component
public class ProjectModuleComponent {
    @Autowired
    private ProjectModuleService projectModuleService;
    /**
     * 分页查询
     * @param page
     * @return
     */
    public ResultHelperPo query (PageHelperPo<ProjectModule, ProjectModulePo> page) {
        return new ResultHelperPo(true, projectModuleService.query(page), "");    }
    /**
     * 加载
     * @param id
     * @return
     */
    public ResultHelperPo load (Long id) {
        return new ResultHelperPo(true, projectModuleService.load(id), "");    }
    /**
     * 修改
     * @param projectModule
     * @return
     */
    public ResultHelperPo save (ProjectModule projectModule) {
        if (null == projectModule.getId()) {            return new ResultHelperPo(false, projectModule, "修改异常");        }        return new ResultHelperPo(true, projectModuleService.save(projectModule), "");    }
    /**
     * 新增
     * @param projectModule
     * @return
     */
    public ResultHelperPo add (ProjectModule projectModule) {
        if (null == projectModule.getId()) {            return new ResultHelperPo(false, projectModule, "新增异常");        }        return new ResultHelperPo(true, projectModuleService.add(projectModule), "");    }
    /**
     * 删除
     * @param id
     * @return
     */
    public ResultHelperPo delete (Long id) {
        projectModuleService.delete(id);        return new ResultHelperPo(true, id, "");    }
}
