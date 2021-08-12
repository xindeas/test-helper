package com.testhelper.controller;

import com.testhelper.po.ResultHelperPo;
import com.testhelper.component.GeneratorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Xindeas
 * @Date: 2021/08/10 17:38
 */
@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class GeneratorController {
    @Autowired
    private GeneratorComponent generatorComponent;

    @GetMapping("/create-code")
    private ResultHelperPo createCode(String tableName) {
        return generatorComponent.createCode(tableName);
    }

    @GetMapping("/get-all-table")
    private ResultHelperPo getAllTable() {
        return generatorComponent.getAllTable();
    }
}
