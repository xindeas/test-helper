package com.testhelper.demo.controller;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.testhelper.demo.component.ProjectModuleComponent;
import com.testhelper.demo.entity.ProjectModule;
import com.testhelper.demo.pojo.ProjectModulePo;
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
