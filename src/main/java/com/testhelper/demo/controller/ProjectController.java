package com.testhelper.demo.controller;

import com.testhelper.demo.component.ProjectComponent;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.ProjectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*", maxAge=3600)
public class ProjectController {
    @Autowired
    private ProjectComponent projectComponent;

    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<Project, ProjectPo> page) {
        return projectComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return projectComponent.load(id);
    }

    /**
     * 修改
     * @param project
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody Project project) {
        return projectComponent.save(project);
    }

    /**
     * 新增
     * @param project
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody Project project) {
        return projectComponent.add(project);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return projectComponent.delete(id);
    }
}
