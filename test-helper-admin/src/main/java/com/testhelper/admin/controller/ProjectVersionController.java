package com.testhelper.admin.controller;

import com.testhelper.admin.component.ProjectVersionComponent;
import com.testhelper.admin.entity.ProjectVersion;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.po.ResultHelperPo;
import com.testhelper.admin.pojo.ProjectVersionPo;
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
     * 校验版本号唯一
     * @param projectId
     * @param no
     * @return
     */
    @GetMapping("/unique-no/{projectId}/{no}")
    private ResultHelperPo checkUnique (@PathVariable("projectId") Long projectId, @PathVariable("no") String no, Long id) {
        return projectVersionComponent.checkUnique(projectId, id, no);
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
