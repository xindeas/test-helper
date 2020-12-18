package com.testhelper.demo.controller;

import com.testhelper.demo.component.DefectComponent;
import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.DefectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:25
 */
@RestController
@RequestMapping("/defect")
@CrossOrigin(origins = "*", maxAge=3600)
public class DefectController {
    @Autowired
    private DefectComponent defectComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<Defect, DefectPo> page) {
        return defectComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return defectComponent.load(id);
    }

    /**
     * 修改
     * @param defect
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody Defect defect) {
        return defectComponent.save(defect);
    }

    /**
     * 新增
     * @param defect
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody Defect defect) {
        return defectComponent.add(defect);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return defectComponent.delete(id);
    }
}
