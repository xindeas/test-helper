package com.testhelper.demo.controller;

import com.testhelper.demo.component.DefectCommentComponent;
import com.testhelper.demo.dto.DefectCommentDto;
import com.testhelper.demo.entity.DefectComment;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.DefectCommentPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 11:42
 */
@RestController
@RequestMapping("/defect-comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DefectCommentController {
    @Autowired
    private DefectCommentComponent defectCommentComponent;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query(@RequestBody PageHelperPo<DefectComment, DefectCommentPo> page) {
        return defectCommentComponent.query(page);
    }

    /**
     * 加载
     *
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load(@PathVariable("id") Long id) {
        return defectCommentComponent.load(id);
    }

    /**
     * 修改
     *
     * @param defectComment
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save(@RequestBody DefectComment defectComment) {
        return defectCommentComponent.save(defectComment);
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add(@RequestBody DefectCommentDto dto) {
        return defectCommentComponent.add(dto);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete(@PathVariable("id") Long id) {
        return defectCommentComponent.delete(id);
    }
}
