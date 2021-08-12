package com.testhelper.controller;

import com.testhelper.component.ProjectAuthComponent;
import com.testhelper.entity.ProjectAuth;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.ProjectAuthPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@RestController
@RequestMapping("/project-auth")
@CrossOrigin(origins = "*", maxAge=3600)
public class ProjectAuthController {
    @Autowired
    private ProjectAuthComponent projectAuthComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<ProjectAuth, ProjectAuthPo> page) {
        return projectAuthComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return projectAuthComponent.load(id);
    }

    /**
     * 修改
     * @param projectAuth
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody ProjectAuth projectAuth) {
        return projectAuthComponent.save(projectAuth);
    }

    /**
     * 新增
     * @param projectAuth
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody ProjectAuth projectAuth) {
        return projectAuthComponent.add(projectAuth);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return projectAuthComponent.delete(id);
    }

}
