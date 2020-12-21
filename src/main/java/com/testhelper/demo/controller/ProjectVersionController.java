package com.testhelper.demo.controller;

import com.testhelper.demo.component.ProjectVersionComponent;
import com.testhelper.demo.entity.ProjectVersion;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.ProjectVersionPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 11:42
 */
@RestController
@RequestMapping("/project-version")
@CrossOrigin(origins = "*", maxAge=3600)
public class ProjectVersionController {
    @Autowired
    private ProjectVersionComponent projectVersionComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<ProjectVersion, ProjectVersionPo> page) {
        return projectVersionComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return projectVersionComponent.load(id);
    }

    /**
     * 修改
     * @param projectVersion
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody ProjectVersion projectVersion) {
        return projectVersionComponent.save(projectVersion);
    }

    /**
     * 新增
     * @param projectVersion
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody ProjectVersion projectVersion) {
        return projectVersionComponent.add(projectVersion);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return projectVersionComponent.delete(id);
    }
}
