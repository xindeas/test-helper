package com.testhelper.demo.controller;

import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.utils.CodeCreator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Xindeas
 * @Date: 2020/12/31 15:25
 */
@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*", maxAge=3600)
public class TableController {
    @GetMapping("/create-code")
    private ResultHelperPo createCode (String tableName) {
        CodeCreator.entityCreator(tableName);
        return new ResultHelperPo(true, null, "");
    }
}
