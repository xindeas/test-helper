package com.testhelper.controller;

import com.testhelper.component.ProjectModuleComponent;
import com.testhelper.entity.ProjectModule;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.ProjectModulePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date:  */
@RestController
@RequestMapping("/project-module")
@CrossOrigin(origins = "*", maxAge=3600)
public class ProjectModuleController {
    @Autowired
    private ProjectModuleComponent projectModuleComponent;
    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<ProjectModule, ProjectModulePo> page) {
        return projectModuleComponent.query(page);
    }
    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return projectModuleComponent.load(id);
    }
    /**
     * 修改
     * @param projectModule
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody ProjectModule projectModule) {
        return projectModuleComponent.save(projectModule);
    }
    /**
     * 新增
     * @param projectModule
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody ProjectModule projectModule) {
        return projectModuleComponent.add(projectModule);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return projectModuleComponent.delete(id);
    }
}
