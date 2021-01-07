package com.testhelper.demo.controller;

import com.testhelper.demo.component.TableComponent;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.utils.CodeCreator;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TableComponent tableComponent;
    @GetMapping("/create-code")
    private ResultHelperPo createCode (String tableName) {
        return tableComponent.createCode(tableName);
    }
    @GetMapping("/get-all-table")
    private ResultHelperPo getAllTable () {
        return tableComponent.getAllTable();
    }
}
