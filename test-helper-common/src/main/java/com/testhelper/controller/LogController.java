package com.testhelper.controller;

import com.testhelper.component.LogComponent;
import com.testhelper.entity.Log;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.LogPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@RestController
@RequestMapping("/log")
@CrossOrigin(origins = "*", maxAge=3600)
public class LogController {
    @Autowired
    private LogComponent logComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<Log, LogPo> page) {
        return logComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return logComponent.load(id);
    }

    /**
     * 修改
     * @param project
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody Log project) {
        return logComponent.save(project);
    }

    /**
     * 新增
     * @param project
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody Log project) {
        return logComponent.add(project);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return logComponent.delete(id);
    }
}
