package com.testhelper.controller;

import com.testhelper.component.DefectCommentComponent;
import com.testhelper.dto.DefectCommentDto;
import com.testhelper.entity.DefectComment;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.DefectCommentPo;
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
