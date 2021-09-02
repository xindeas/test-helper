package com.testhelper.controller;

import com.testhelper.po.ResultHelperPo;
import com.testhelper.component.GeneratorComponent;
import com.testhelper.utils.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @GetMapping("/get-all-table")
    private ResultHelperPo getAllTable() {
        return generatorComponent.getAllTable();
    }

    @RequestMapping("/download")
    public void download(String tableName, HttpServletResponse response) throws IOException {
        byte[] data = VelocityUtils.downloadCode(tableName);
        response.reset();
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=fun.zip");
        response.addHeader("Content-Length", "" + data.length);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
