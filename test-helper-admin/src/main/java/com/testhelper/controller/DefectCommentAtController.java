package com.testhelper.controller;

import com.testhelper.component.DefectCommentAtComponent;
import com.testhelper.entity.DefectCommentAt;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.DefectCommentAtPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 11:42
 */
@RestController
@RequestMapping("/defect-comment-at")
@CrossOrigin(origins = "*", maxAge=3600)
public class DefectCommentAtController {
    @Autowired
    private DefectCommentAtComponent defectCommentAtComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<DefectCommentAt, DefectCommentAtPo> page) {
        return defectCommentAtComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return defectCommentAtComponent.load(id);
    }

    /**
     * 修改
     * @param defectCommentAt
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody DefectCommentAt defectCommentAt) {
        return defectCommentAtComponent.save(defectCommentAt);
    }

    /**
     * 新增
     * @param defectCommentAt
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody DefectCommentAt defectCommentAt) {
        return defectCommentAtComponent.add(defectCommentAt);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return defectCommentAtComponent.delete(id);
    }
}
